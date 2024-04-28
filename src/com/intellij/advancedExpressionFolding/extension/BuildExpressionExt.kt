package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.Companion.getInstance
import com.intellij.advancedExpressionFolding.UnexpectedException
import com.intellij.advancedExpressionFolding.expression.*
import com.intellij.advancedExpressionFolding.extension.PsiClassExt.createExpression
import com.intellij.advancedExpressionFolding.extension.PsiDeclarationStatementEx.createExpression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.progress.ProcessCanceledException
import com.intellij.openapi.project.IndexNotReadyException
import com.intellij.openapi.util.Key
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.psi.util.CachedValue
import com.intellij.psi.util.CachedValueProvider
import com.intellij.psi.util.CachedValuesManager
import com.intellij.psi.util.PsiModificationTracker
import com.intellij.util.containers.ContainerUtil
import com.intellij.util.containers.IntObjectMap
import org.jetbrains.annotations.Contract
import java.lang.ref.WeakReference
import java.util.*

object BuildExpressionExt {
    private val KEY_MAP: IntObjectMap<Key<CachedValue<WeakReference<Expression>>>> =
        ContainerUtil.createIntKeyWeakValueMap()
    private val NO_DESCRIPTORS: Array<FoldingDescriptor> = emptyArray()
    private val NULL_EXPRESSION: Expression = NullExpression()
    

    // 💩💩💩 Define the AdvancedExpressionFoldingProvider extension point
    @Contract("_, _, true -> !null")
    fun buildExpression(element: PsiElement, document: Document, synthetic: Boolean): Expression? {
            val settings = getInstance()
            when (element) {
                is PsiForStatement -> ForStatementExpressionExt.getForStatementExpression(element, document)?.let { return it }
                is PsiForeachStatement -> LoopExt.getForeachStatementExpression(element)?.let { return it }
                is PsiIfStatement -> IfExt.getIfExpression(element, document)?.let { return it }
                is PsiWhileStatement -> LoopExt.getWhileStatement(element)?.let { return it }
                is PsiJavaToken -> if (element.tokenType === JavaTokenType.SEMICOLON && settings.state.semicolonsCollapse && !element.isWritable()) {
                    return SemicolonExpression(element, element.getTextRange())
                }
                is PsiCatchSection -> {
                    val settings1 = getInstance()
                    var expression: Expression? = null
                    if (element.parameter != null && (element.lParenth != null) && (element.rParenth != null
                                ) && settings1.state.compactControlFlowSyntaxCollapse
                    ) {
                        expression = CompactControlFlowExpression(
                            element,
                            TextRange.create(
                                element.lParenth!!.textRange.startOffset,
                                element.rParenth!!.textRange.endOffset
                            )
                        )
                    }
                    expression?.let { return it }
                }
                is PsiDoWhileStatement -> LoopExt.getDoWhileStatement(element)?.let { return it }
                is PsiSwitchStatement -> IfExt.getSwitchStatement(element)?.let { return it }
                is PsiArrayAccessExpression -> if (settings.state.getExpressionsCollapse) {
                    PsiArrayAccessExpressionExt.getArrayAccessExpression(element, document)?.let { return it }
                }
                is PsiMethodCallExpression -> MethodCallExpressionExt.getMethodCallExpression(element, document)?.let { return it }
                is PsiReferenceExpression -> ReferenceExpressionExt.getReferenceExpression(element)?.let { return it }
                is PsiNewExpression -> NewExpressionExt.getNewExpression(element, document)?.let { return it }
                is PsiLiteralExpression -> LiteralExpressionExt.getLiteralExpression(element)?.let { return it }
                is PsiAssignmentExpression -> AssignmentExpressionExt.getAssignmentExpression(element, document)?.let { return it }
                is PsiPolyadicExpression -> IfExt.getPolyadicExpression(element, document)?.let { return it }
                is PsiBinaryExpression -> BinaryExpressionExt.getBinaryExpression(element, document)?.let { return it }
                is PsiConditionalExpression -> IfExt.getConditionalExpression(element, document)?.let { return it }
                is PsiPrefixExpression -> PrefixExpressionExt.getPrefixExpression(element, document)?.let { return it }
                is PsiParenthesizedExpression -> if (settings.state.castExpressionsCollapse) {
                    val e = element.expression
                    if (e is PsiTypeCastExpression) {
                        PsiTypeCastExpressionExt.getTypeCastExpression(e, document)?.let {
                            return TypeCast(element, element.getTextRange(), it.getObject())
                        }
                    }
                    if (e != null) {
                        getExpression(e, document, synthetic)?.let { return it }
                    }
                }
                is PsiTypeCastExpression -> if (settings.state.castExpressionsCollapse) {
                    PsiTypeCastExpressionExt.getTypeCastExpression(element, document)?.let { return it }
                }
                is PsiDeclarationStatement -> createExpression(element, document)?.let { return it }
                is PsiVariable -> if (settings.state.varExpressionsCollapse
                    && (element.getParent() is PsiDeclarationStatement || element.getParent() is PsiForeachStatement)) {
                    PsiVariableExt.getVariableDeclaration(element)?.let { return it }
                }
                is PsiCodeBlock -> PsiCodeBlockExt.getCodeBlockExpression(element)?.let { return it }
                is PsiClass -> createExpression(element)?.let { return it }
            }
            if (synthetic) {
                val children = ArrayList<Expression>()
                Helper.findChildExpressions(element, children, document)
                return SyntheticExpressionImpl(element, element.textRange, document.getText(element.textRange), children)
            }
            return null
    }

    @Contract("_, _, true -> !null")
    fun getExpression(element: PsiElement, document: Document, synthetic: Boolean): Expression? {
        val key = getKey(document, synthetic)
        val cachedValue = CachedValuesManager.getCachedValue(
            element, key
        ) {
            CachedValueProvider.Result.create(
                WeakReference(buildExpression(element, document, synthetic) ?: NULL_EXPRESSION),
                PsiModificationTracker.MODIFICATION_COUNT
            )
        }

        var weakRef = cachedValue.get()
        if (weakRef == null) {
            element.putUserData(key, null)
            weakRef = getExpression(element, document, synthetic)
        } else if (weakRef === NULL_EXPRESSION) {
            return null
        }
        return weakRef
    }

    private fun getKey(document: Document, synthetic: Boolean): Key<CachedValue<WeakReference<Expression>>> {
        val key = document.hashCode() + (if (synthetic) 1 else 0)
        KEY_MAP.get(key)?.let { return it }
        val create = Key.create<CachedValue<WeakReference<Expression>>>(key.toString())
        KEY_MAP.put(key, create)
        return create
    }

    @JvmStatic
    @Throws(IndexNotReadyException::class)
    fun getAnyExpression(element: PsiElement, document: Document?): Expression {
        return getExpression(element, document!!, true)!!
    }

    /**
     * TODO: Think how we can prevent IndexNotReadyException (e.g. via "is dumb mode")
     */
    @JvmStatic
    @Throws(IndexNotReadyException::class)
    fun getNonSyntheticExpression(element: PsiElement, document: Document?): Expression? {
        return getExpression(element, document!!, false)
    }

    @JvmStatic
    fun collectFoldRegionsRecursively(
        element: PsiElement,
        document: Document,
        quick: Boolean
    ): Array<FoldingDescriptor> {
        var lastElement = element
        var allDescriptors: MutableList<FoldingDescriptor?>? = null
        try {
            val expression = getNonSyntheticExpression(element, document)
            if (expression != null && expression.supportsFoldRegions(document, null)) {
                val descriptors = expression.buildFoldRegions(expression.element, document, null)
                allDescriptors = ArrayList()
                Collections.addAll(allDescriptors, *descriptors)
            }
            if (expression == null || expression.isNested) {
                for (child in element.children) {
                    lastElement = child
                    val descriptors = collectFoldRegionsRecursively(child, document, quick)
                    if (descriptors.isNotEmpty()) {
                        if (allDescriptors == null) {
                            allDescriptors = ArrayList()
                        }
                        Collections.addAll(allDescriptors, *descriptors)
                    }
                }
            }
        } catch (ignore: IndexNotReadyException) {
        } catch (ignore: ProcessCanceledException) {
        } catch (e: UnexpectedException) {
            throw e
        } catch (e: Exception) {
            var fileName: String? = null
            val containingFile = lastElement.containingFile
            if (containingFile != null) {
                fileName = containingFile.name
            }
            throw UnexpectedException(lastElement.javaClass, lastElement.text, lastElement.textRange, fileName, e)
        }
        return allDescriptors?.filterNotNull()?.toTypedArray() ?: NO_DESCRIPTORS
    }

}
