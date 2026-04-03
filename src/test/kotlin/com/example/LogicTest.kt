package com.example

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class LogicTest {
    @Test
    fun adultAt18() {
        assertTrue(Logic().isAdult(18))
    }
}
