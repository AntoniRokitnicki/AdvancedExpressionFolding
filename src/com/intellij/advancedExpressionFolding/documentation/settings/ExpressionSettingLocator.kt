package com.intellij.advancedExpressionFolding.documentation.settings

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import java.util.HashSet
import java.util.LinkedHashSet
import kotlin.reflect.full.memberProperties

object ExpressionSettingLocator {
  private val knownKeys: Set<String> = AdvancedExpressionFoldingSettings.State::class.memberProperties
    .filter { it.returnType.classifier == Boolean::class }
    .map { it.name }
    .toSet()

  fun settingKeysFor(expression: Expression): List<String> {
    val discovered = LinkedHashSet<String>()
    collect(expression::class.java, discovered, HashSet())
    return discovered.filter { it in knownKeys }
  }

  private fun collect(type: Class<*>, result: MutableSet<String>, visited: MutableSet<Class<*>>) {
    if (!visited.add(type)) return
    type.kotlin.memberProperties
      .filter { it.returnType.classifier == Boolean::class }
      .mapTo(result) { it.name }
    type.interfaces.forEach { collect(it, result, visited) }
    type.superclass?.let { collect(it, result, visited) }
  }
}
