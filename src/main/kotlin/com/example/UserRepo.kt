package com.example

data class User(val id: Int, val name: String)

class UserRepo {
    private val users = listOf(User(1, "A"), User(2, "B"))

    fun findName(id: Int): String? = users.firstOrNull { it.id == id }?.name
}
