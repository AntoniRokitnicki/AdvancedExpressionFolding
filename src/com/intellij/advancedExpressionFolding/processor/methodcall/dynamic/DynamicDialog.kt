package com.intellij.advancedExpressionFolding.processor.methodcall.dynamic

import com.intellij.advancedExpressionFolding.processor.methodcall.MethodName
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBTextField
import com.intellij.ui.dsl.builder.AlignX
import com.intellij.ui.dsl.builder.panel

enum class Action {
    RENAME, REMOVE, CANCEL
}

fun MethodName.showRenameDialog(): Pair<Action, MethodName>? {
    var selectedAction: Action? = null
    var newMethodName: MethodName? = null

    val name = this
    val textField = JBTextField(name.value, 20)

    val dialogWrapper = object : DialogWrapper(true) {
        init {
            title = "Choose a new folding for method $name"
            init()
        }

        override fun createCenterPanel() = panel {
            row("") {
                cell(textField)
                    .align(AlignX.LEFT)
                    .focused()
            }
            row("") {
                button("Remove") {
                    selectedAction = Action.REMOVE
                    close(OK_EXIT_CODE)
                }
            }
        }

        override fun getPreferredFocusedComponent() = textField

        override fun doOKAction() {
            selectedAction = Action.RENAME
            newMethodName = MethodName(textField.text)
            super.doOKAction()
        }

        override fun doCancelAction() {
            selectedAction = Action.CANCEL
            super.doCancelAction()
        }
    }

    dialogWrapper.show()

    return when (selectedAction) {
        Action.RENAME -> newMethodName?.takeIf {
            it.value.isNotBlank()
        }?.let {
            Action.RENAME to it
        }
        Action.REMOVE -> Action.REMOVE to name
        else -> null
    }
}
