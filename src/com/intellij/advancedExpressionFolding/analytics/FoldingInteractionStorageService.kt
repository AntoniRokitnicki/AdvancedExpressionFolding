package com.intellij.advancedExpressionFolding.analytics

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.service
import com.intellij.util.xmlb.annotations.Attribute
import com.intellij.util.xmlb.annotations.Tag
import com.intellij.util.xmlb.annotations.XCollection

@Service(Service.Level.APP)
@State(
    name = "AdvancedExpressionFoldingInteractionStorage",
    storages = [Storage("advancedExpressionFoldingInteractions.xml")],
)
class FoldingInteractionStorageService : PersistentStateComponent<FoldingInteractionStorageService.State> {

    @Tag("records")
    data class State(
        @XCollection(style = XCollection.Style.v2)
        var records: MutableList<Record> = mutableListOf(),
    )

    @Tag("record")
    data class Record(
        @Attribute("elementType")
        var elementType: String = "",
        @Attribute("textLength")
        var textLength: Int = 0,
        @Attribute("lineCount")
        var lineCount: Int = 0,
        @Attribute("label")
        var label: Int = 0,
    )

    private var state = State()

    override fun getState(): State = state

    override fun loadState(state: State) {
        this.state = state
    }

    fun addRecord(record: Record) {
        state.records.add(record)
    }

    companion object {
        fun getInstance(): FoldingInteractionStorageService = service()
    }
}
