package com.intellij.advancedExpressionFolding.diff

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings
import com.intellij.diff.DiffContentFactory
import com.intellij.diff.requests.DiffRequest
import com.intellij.diff.requests.SimpleDiffRequest
import com.intellij.ide.highlighter.JavaFileType
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.UserDataHolder
import com.intellij.openapi.vcs.changes.Change
import com.intellij.openapi.vcs.changes.ContentRevision
import com.intellij.openapi.vcs.changes.actions.diff.ChangeDiffRequestProducer
import com.intellij.openapi.vcs.changes.actions.diff.ChangeDiffRequestProvider
import com.intellij.util.ThreeState


class DiffRequestProvider : ChangeDiffRequestProvider, AdvancedExpressionFoldingSettings.ConfigDelegate() {
    override fun isEquals(change1: Change, change2: Change) = ThreeState.UNSURE

    override fun canCreate(project: Project?, change: Change) =
        testDataFoldingDiff && change.afterRevision?.file?.path?.contains("/testData/") == true

    override fun process(
        presentable: ChangeDiffRequestProducer,
        context: UserDataHolder,
        indicator: ProgressIndicator
    ): DiffRequest {
        val change = presentable.change

        val factory = DiffContentFactory.getInstance()
        fun modifyContent(contentRevision: ContentRevision?) = contentRevision?.content?.let {
            factory.create(FoldTagsParser.asVisible(it), JavaFileType.INSTANCE)
        } ?: factory.createEmpty()

        return SimpleDiffRequest(
            "Folding TestData Diff",
            modifyContent(change.beforeRevision),
            modifyContent(change.afterRevision),
            change.beforeRevision?.file?.name ?: "(empty)",
            change.afterRevision?.file?.name ?: "(empty)"
        )
    }

}
