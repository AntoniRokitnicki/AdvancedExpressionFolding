package com.intellij.advancedExpressionFolding.discovery

import com.intellij.openapi.application.ReadAction
import com.intellij.openapi.editor.Document
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ProjectFileIndex
import com.intellij.openapi.util.TextRange
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiRecursiveElementWalkingVisitor
import com.intellij.psi.PsiStatement
import com.intellij.psi.PsiWhiteSpace
import com.intellij.psi.PsiIfStatement
import com.intellij.psi.util.PsiTreeUtil
import java.security.MessageDigest
import java.util.Locale
import kotlin.math.max
import kotlin.math.min

internal class RuleDiscoveryScanner(
    private val maxSamplesPerFile: Int = 400
) {

    fun collect(project: Project, indicator: ProgressIndicator): List<CandidateOccurrence> {
        val files = mutableListOf<VirtualFile>()
        val fileIndex = ProjectFileIndex.getInstance(project)
        fileIndex.iterateContent { file ->
            ProgressManager.checkCanceled()
            if (!file.isValid || file.isDirectory || file.fileType.isBinary) {
                return@iterateContent true
            }
            files.add(file)
            true
        }

        if (files.isEmpty()) {
            return emptyList()
        }

        val psiManager = com.intellij.psi.PsiManager.getInstance(project)
        val documentManager = PsiDocumentManager.getInstance(project)
        val occurrences = mutableListOf<CandidateOccurrence>()

        files.forEachIndexed { index, file ->
            ProgressManager.checkCanceled()
            indicator.checkCanceled()
            indicator.fraction = index.toDouble() / files.size.coerceAtLeast(1)
            indicator.text = "Scanning ${file.presentableName()}"

            val fileOccurrences = ReadAction.compute<List<CandidateOccurrence>, RuntimeException> {
                val psiFile = psiManager.findFile(file) ?: return@compute emptyList()
                if (psiFile.language.id.isNullOrEmpty()) {
                    return@compute emptyList()
                }
                val document = documentManager.getDocument(psiFile) ?: return@compute emptyList()
                val collector = CandidateCollector(
                    psiFile,
                    document,
                    file.timeStamp,
                    hashPath(file)
                )
                psiFile.accept(collector)
                collector.results()
            }
            occurrences.addAll(fileOccurrences)
        }

        return occurrences
    }

    private fun VirtualFile.presentableName(): String = nameSequence.toString()

    private fun hashPath(file: VirtualFile): String {
        val digest = MessageDigest.getInstance("SHA-1")
        val input = file.path.toByteArray()
        val hashed = digest.digest(input)
        return hashed.joinToString(separator = "") { String.format(Locale.US, "%02x", it) }
    }

    private inner class CandidateCollector(
        private val psiFile: PsiFile,
        private val document: Document,
        private val timestamp: Long,
        private val fileHash: String
    ) : PsiRecursiveElementWalkingVisitor() {

        private val seenRanges = HashSet<TextRange>()
        private val results = mutableListOf<CandidateOccurrence>()
        private var collected = 0

        override fun visitElement(element: PsiElement) {
            if (collected >= maxSamplesPerFile) {
                return
            }
            ProgressManager.checkCanceled()
            when (element) {
                is PsiMethodCallExpression -> handleMethodCall(element)
                is PsiIfStatement -> handleGuardIf(element)
            }
            super.visitElement(element)
        }

        fun results(): List<CandidateOccurrence> = results

        private fun handleMethodCall(call: PsiMethodCallExpression) {
            val methodName = call.methodExpression.referenceName ?: return
            val chainLength = computeChainLength(call)
            val text = call.text.lowercase(Locale.US)
            val hasLoggerCall = LOGGER_NAMES.contains(methodName.lowercase(Locale.US)) || text.contains("logger")
            val longParameterList = call.argumentList.expressions.size >= 6
            val builderStyle = methodName in BUILDER_METHODS && text.contains("builder")

            if (chainLength > 1) {
                addCandidate(call, CandidateFamily.CHAIN_CALL, chainLength = chainLength, hasLoggerCall = hasLoggerCall)
            }

            if (hasLoggerCall) {
                addCandidate(call, CandidateFamily.LOGGER_LINE, chainLength = chainLength, hasLoggerCall = true)
            }

            if (longParameterList) {
                addCandidate(call, CandidateFamily.LONG_PARAMETER_LIST, chainLength = chainLength, hasLoggerCall = hasLoggerCall)
            }

            if (builderStyle) {
                addCandidate(call, CandidateFamily.BUILDER_BLOCK, chainLength = chainLength, hasLoggerCall = hasLoggerCall)
            }
        }

        private fun handleGuardIf(statement: PsiIfStatement) {
            val conditionText = statement.condition?.text?.lowercase(Locale.US) ?: return
            if (!conditionText.contains("null") && !conditionText.contains("instanceof")) {
                return
            }
            val thenBranch = statement.thenBranch ?: return
            val statements = PsiTreeUtil.getChildrenOfTypeAsList(thenBranch, PsiStatement::class.java)
            val isGuard = statements.any { stmt ->
                val text = stmt.text.lowercase(Locale.US)
                text.contains("return") || text.contains("throw") || text.contains("continue")
            }
            if (isGuard) {
                addCandidate(statement, CandidateFamily.GUARD_IF, hasNullCheck = true)
            }
        }

        private fun addCandidate(
            element: PsiElement,
            family: CandidateFamily,
            chainLength: Int = 0,
            hasLoggerCall: Boolean = false,
            hasNullCheck: Boolean = false
        ) {
            if (collected >= maxSamplesPerFile) {
                return
            }
            val range = element.textRange ?: return
            if (!seenRanges.add(range)) {
                return
            }

            val lineInfo = computeLineInfo(range)
            val commentDensity = computeCommentDensity(lineInfo.windowRange)
            val indentLevel = indentLevel(lineInfo.startLine)
            val nullCheck = hasNullCheck || element.text.contains("null", ignoreCase = true)
            val loggerFlag = hasLoggerCall || element.text.contains("logger", ignoreCase = true)
            val histogram = nestingHistogram(element)
            val features = buildFeatures(
                element,
                family,
                chainLength,
                loggerFlag,
                nullCheck,
                commentDensity,
                indentLevel,
                histogram
            )
            val snippet = document.getText(lineInfo.captureRange).trimEnd()
            val metadata = CandidateMetadata(
                fileHash = fileHash,
                psiKind = element.javaClass.simpleName,
                lineRange = IntRange(lineInfo.startLine + 1, lineInfo.endLine + 1),
                snippet = snippet,
                fileExtension = psiFile.virtualFile?.extension,
                indentLevel = indentLevel,
                textRange = range
            )
            val occurrenceId = buildId(range, family)
            results += CandidateOccurrence(
                id = occurrenceId,
                language = psiFile.language.id,
                foldType = family.id,
                features = features,
                timestamp = timestamp,
                metadata = metadata
            )
            collected++
        }

        private fun buildId(range: TextRange, family: CandidateFamily): String {
            val fileId = psiFile.virtualFile?.path ?: psiFile.name
            return "$fileId:${range.startOffset}-${range.endOffset}:${family.id}"
        }

        private fun computeChainLength(call: PsiMethodCallExpression): Int {
            var length = 1
            var qualifier = call.methodExpression.qualifierExpression
            while (qualifier is PsiMethodCallExpression) {
                length++
                qualifier = qualifier.methodExpression.qualifierExpression
            }
            return length
        }

        private fun computeLineInfo(range: TextRange): LineInfo {
            val startLine = document.getLineNumber(range.startOffset)
            val endLine = document.getLineNumber(range.endOffset)
            val captureEndLine = min(document.lineCount - 1, endLine + 1)
            val captureRange = TextRange(
                document.getLineStartOffset(startLine),
                document.getLineEndOffset(captureEndLine)
            )
            val windowStartLine = max(0, startLine - 2)
            val windowEndLine = min(document.lineCount - 1, endLine + 2)
            val windowRange = TextRange(
                document.getLineStartOffset(windowStartLine),
                document.getLineEndOffset(windowEndLine)
            )
            return LineInfo(startLine, endLine, captureRange, windowRange)
        }

        private fun computeCommentDensity(windowRange: TextRange): Double {
            val comments = PsiTreeUtil.collectElements(psiFile) { element ->
                element is PsiComment && element.textRange.intersects(windowRange)
            }
            val commentLength = comments.sumOf { it.textRange.length }
            val windowLength = windowRange.length.coerceAtLeast(1)
            return commentLength.toDouble() / windowLength.toDouble()
        }

        private fun indentLevel(line: Int): Int {
            if (line < 0 || line >= document.lineCount) {
                return 0
            }
            val startOffset = document.getLineStartOffset(line)
            val endOffset = document.getLineEndOffset(line)
            val text = document.getText(TextRange(startOffset, endOffset))
            var count = 0
            for (char in text) {
                if (char == ' ') {
                    count++
                } else if (char == '\t') {
                    count += 4
                } else if (!char.isWhitespace()) {
                    break
                }
            }
            return count / 4
        }

        private fun nestingHistogram(element: PsiElement): IntArray {
            val histogram = IntArray(4)
            var current: PsiElement? = element.parent
            while (current != null && current !is PsiFile) {
                when (current) {
                    is PsiIfStatement -> histogram[0]++
                    is com.intellij.psi.PsiLoopStatement -> histogram[1]++
                    is com.intellij.psi.PsiTryStatement -> histogram[2]++
                    is com.intellij.psi.PsiLambdaExpression -> histogram[3]++
                }
                current = current.parent
            }
            return histogram
        }

        private fun childCount(element: PsiElement): Int = element.children.count { it !is PsiWhiteSpace }

        private fun siblingCount(element: PsiElement): Int {
            val parent = element.parent ?: return 0
            return parent.children.count { it !is PsiWhiteSpace }
        }

        private fun buildFeatures(
            element: PsiElement,
            family: CandidateFamily,
            chainLength: Int,
            hasLoggerCall: Boolean,
            hasNullCheck: Boolean,
            commentDensity: Double,
            indentLevel: Int,
            histogram: IntArray
        ): DoubleArray {
            val range = element.textRange
            val startLine = document.getLineNumber(range.startOffset)
            val endLine = document.getLineNumber(range.endOffset)
            val features = DoubleArray(FeatureDictionary.FEATURE_COUNT)
            features[0] = psiFile.language.id.hashCode().toDouble()
            features[1] = (psiFile.virtualFile?.extension?.hashCode() ?: 0).toDouble()
            features[2] = (element.node?.elementType?.toString()?.hashCode() ?: 0).toDouble()
            features[3] = family.id.hashCode().toDouble()
            features[4] = depth(element).toDouble()
            features[5] = element.textLength.toDouble()
            features[6] = (endLine - startLine + 1).toDouble()
            features[7] = siblingCount(element).toDouble()
            features[8] = childCount(element).toDouble()
            features[9] = chainLength.toDouble()
            features[10] = if (hasLoggerCall) 1.0 else 0.0
            features[11] = if (hasNullCheck) 1.0 else 0.0
            features[12] = commentDensity
            features[13] = indentLevel.toDouble()
            features[14] = histogram[0].toDouble()
            features[15] = histogram[1].toDouble()
            features[16] = histogram[2].toDouble()
            features[17] = histogram[3].toDouble()
            return features
        }

        private fun depth(element: PsiElement): Int {
            var depth = 0
            var current: PsiElement? = element.parent
            while (current != null && current !is PsiFile) {
                depth++
                current = current.parent
            }
            return depth
        }
    }

    private data class LineInfo(
        val startLine: Int,
        val endLine: Int,
        val captureRange: TextRange,
        val windowRange: TextRange
    )

    companion object {
        private val LOGGER_NAMES = setOf("debug", "info", "warn", "warning", "error", "trace")
        private val BUILDER_METHODS = setOf("build", "builder", "create", "with")
    }
}
