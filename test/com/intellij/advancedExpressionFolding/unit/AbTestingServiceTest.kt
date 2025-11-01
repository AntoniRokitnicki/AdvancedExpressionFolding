package com.intellij.advancedExpressionFolding.unit

import com.intellij.advancedExpressionFolding.ab.AbTestingService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class AbTestingServiceTest {

    @Test
    fun `variant is stable for the same experiment`() {
        val seed = 42L
        val service = AbTestingService { seed }

        val first = service.variant("folding-speed")
        val second = service.variant("folding-speed")

        assertEquals(first, second)
        assertEquals(expectedVariant(seed, "folding-speed", listOf("control", "treatment")), first)
    }

    @Test
    fun `assignments survive serialization`() {
        val initialService = AbTestingService { 7L }
        val assigned = initialService.variant("folding-speed")

        val storedState = initialService.snapshotState()

        val restoredService = AbTestingService { 99L }
        restoredService.loadState(storedState)

        val restored = restoredService.variant("folding-speed")
        assertEquals(assigned, restored)
    }

    @Test
    fun `variant recalculates when requested variant list changes`() {
        val service = AbTestingService { 1L }
        val original = service.variant("folding-speed", listOf("control", "treatment"))

        val alternatives = listOf("baseline", "enhanced", "aggressive")
        val reassigned = service.variant("folding-speed", alternatives)

        assertNotEquals(original, reassigned)
        assertEquals(expectedVariant(1L, "folding-speed", alternatives), reassigned)
    }

    private fun expectedVariant(seed: Long, experiment: String, variants: List<String>): String {
        val mix = sanitize(seed xor experiment.hashCode().toLong())
        val index = (mix % variants.size).toInt()
        return variants[index]
    }

    private fun sanitize(value: Long): Long {
        return if (value == Long.MIN_VALUE) 0 else kotlin.math.abs(value)
    }
}
