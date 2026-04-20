package com.intellij.advancedExpressionFolding.processor.cache

import com.intellij.advancedExpressionFolding.BaseTest
import com.intellij.advancedExpressionFolding.FoldingService
import com.intellij.advancedExpressionFolding.expression.SyntheticExpressionImpl
import com.intellij.advancedExpressionFolding.processor.declaration.PsiClassExt
import com.intellij.advancedExpressionFolding.processor.getSignature
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.util.Computable
import com.intellij.openapi.util.Key
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiField
import com.intellij.psi.PsiJavaFile
import com.intellij.psi.util.MethodSignature
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class KeysTest : BaseTest() {

    @Test
    fun `values include every key property`() {
        val keysFromValues = readAction { Keys.allKeys }
        val keysFromProperties = readAction { expectedKeys() }

        val missingFromValues = keysFromProperties - keysFromValues
        val missingFromProperties = keysFromValues - keysFromProperties
        assertEquals(emptySet<Key<*>>(), missingFromValues, "values is missing: ${missingFromValues.map { it.toString() }}")
        assertEquals(emptySet<Key<*>>(), missingFromProperties, "values has extra: ${missingFromProperties.map { it.toString() }}")
    }

    @Test
    fun `service clear removes all key values`() {
        val childClass = createChildClass()
        val field = readAction { childClass.fields.single() }
        val overrideMethod = readAction { childClass.findMethodsByName("overrideMe", false).single() }
        val parentMethod = readAction { childClass.superClass!!.findMethodsByName("overrideMe", false).single() }
        val signature = readAction { overrideMethod.getSignature() }
        val syntheticExpression = readAction {
            SyntheticExpressionImpl(childClass, childClass.textRange, "synthetic", arrayListOf())
        }

        readAction {
            Keys.allKeys.forEach { key ->
                putValue(childClass, field, signature, parentMethod.containingClass!!.name!!, syntheticExpression, key)
            }
        }

        readAction {
            val visitor = FoldingService.KeyCleanerPsiElementVisitor()
            childClass.accept(visitor)
        }

        readAction {
            Keys.allKeys.forEach { key ->
                @Suppress("UNCHECKED_CAST")
                assertNull(childClass.getUserData(key as Key<Any?>))
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun putValue(
        psiClass: PsiClass,
        field: PsiField,
        signature: MethodSignature,
        parentName: String,
        expression: SyntheticExpressionImpl,
        key: Key<*>,
    ) {
        val syntheticKey = Keys.getKey(true)
        val nonSyntheticKey = Keys.getKey(false)
        val syntheticVersionKey = Keys.getVersionKey(true)
        val nonSyntheticVersionKey = Keys.getVersionKey(false)

        when (key) {
            Keys.BUILDER -> psiClass.putUserData(key as Key<Boolean>, true)
            Keys.CLASS_TYPE_KEY -> psiClass.putUserData(key as Key<PsiClassExt.ClassType>, PsiClassExt.ClassType.BUILDER)
            Keys.FIELD_META_DATA_KEY -> psiClass.putUserData(key as Key<Keys.FieldMetaData>, Keys.FieldMetaData())
            Keys.IGNORED -> psiClass.putUserData(key as Key<Boolean>, true)
            syntheticKey -> psiClass.putUserData(key as Key<SyntheticExpressionImpl>, expression)
            nonSyntheticKey -> psiClass.putUserData(key as Key<SyntheticExpressionImpl>, expression)
            syntheticVersionKey -> psiClass.putUserData(key as Key<Int>, 1)
            nonSyntheticVersionKey -> psiClass.putUserData(key as Key<Int>, 2)
            Keys.FIELD_KEY -> psiClass.putUserData(key as Key<PsiField>, field)
            Keys.FULL_CACHE -> psiClass.putUserData(key as Key<Array<FoldingDescriptor>>, emptyArray<FoldingDescriptor>())
            Keys.METHOD_TO_PARENT_CLASS_KEY -> psiClass.putUserData(
                key as Key<MutableMap<MethodSignature, String>>,
                mutableMapOf(signature to parentName)
            )
            else -> error("Unsupported key $key")
        }
    }

    private fun expectedKeys(): Set<Key<*>> = setOf(
        Keys.BUILDER,
        Keys.CLASS_TYPE_KEY,
        Keys.FIELD_META_DATA_KEY,
        Keys.IGNORED,
        Keys.getKey(true),
        Keys.getKey(false),
        Keys.getVersionKey(true),
        Keys.getVersionKey(false),
        Keys.FIELD_KEY,
        Keys.FULL_CACHE,
        Keys.METHOD_TO_PARENT_CLASS_KEY,
    )

    private fun createChildClass(): PsiClass {
        val file = fixture.configureByText(
            "SummaryParentOverrideWithField.java",
            """
            class Parent {
                void overrideMe() {}
            }

            class Child extends Parent {
                private int value;

                @Override
                void overrideMe() {
                    value++;
                }
            }
            """.trimIndent()
        ) as PsiJavaFile
        return readAction { file.classes.single { it.name == "Child" } }
    }

    private fun <T> readAction(action: () -> T): T =
        runReadAction(action)

    private fun <T> runReadAction(action: () -> T): T =
        ApplicationManager.getApplication().runReadAction(Computable(action))
}
