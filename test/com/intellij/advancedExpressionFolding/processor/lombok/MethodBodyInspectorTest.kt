package com.intellij.advancedExpressionFolding.processor.lombok

import com.intellij.advancedExpressionFolding.BaseTest
import com.intellij.advancedExpressionFolding.processor.lombok.MethodBodyInspector.asWrapperSetter
import com.intellij.openapi.application.runReadAction
import com.intellij.psi.PsiClass
import com.intellij.psi.util.PsiTreeUtil
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MethodBodyInspectorTest : BaseTest() {

    @Test
    fun wrapperSetterDetectsCollectionsWrapper() {
        val file = fixture.configureByText(
            "Wrapper.java",
            """
            import java.util.Collections;
            import java.util.List;
            
            class Wrapper {
                private List<String> values;
                
                void setValues(List<String> values) {
                    this.values = Collections.unmodifiableList(values);
                }
            }
            """.trimIndent()
        )

        val result = runReadAction {
            val psiClass = PsiTreeUtil.findChildOfType(file, PsiClass::class.java)!!
            val field = psiClass.fields.single { it.name == "values" }
            val method = psiClass.findMethodsByName("setValues", false).single()
            method.asWrapperSetter(field)
        }

        assertEquals("wrapper = Collections::unmodifiableList", result)
    }

    @Test
    fun wrapperSetterDetectsThisQualifiedCall() {
        val file = fixture.configureByText(
            "WrapperLocal.java",
            """
            import java.util.List;
            
            class WrapperLocal {
                private List<String> values;
                
                void setValues(List<String> values) {
                    this.values = this.localWrap(values);
                }
                
                private List<String> localWrap(List<String> input) {
                    return input;
                }
            }
            """.trimIndent()
        )

        val result = runReadAction {
            val psiClass = PsiTreeUtil.findChildOfType(file, PsiClass::class.java)!!
            val field = psiClass.fields.single { it.name == "values" }
            val method = psiClass.findMethodsByName("setValues", false).single()
            method.asWrapperSetter(field)
        }

        assertEquals("wrapper = this::localWrap", result)
    }
}
