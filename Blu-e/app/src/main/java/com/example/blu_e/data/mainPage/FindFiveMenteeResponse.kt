package com.example.blu_e.data.mainPage

import com.example.blu_e.data.User

data class FindFiveMenteeResponse (
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: User
) {
    data class FindFiveMenteeItems(
        val userId: String,
        val nickname: String,
        val userImg: String
    )
}