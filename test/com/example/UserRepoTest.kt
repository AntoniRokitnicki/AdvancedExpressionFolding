package com.example

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class UserRepoTest {
    @Test
    fun returnsNameViaSafeCalls() {
        val repo = UserRepo()

        assertEquals("B", repo.findName(2))
    }
}
