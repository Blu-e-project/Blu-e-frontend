package com.example.blu_e.data.mainPage

data class FindFiveMenteeResponse (
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: ArrayList<FindFiveMenteeItems>?
)

data class FindFiveMenteeItems(
        val userId: Int,
        val nickname: String,
        val userImg: String,
        val role: Int
    )
