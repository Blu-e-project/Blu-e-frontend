package com.example.blu_e.data.mainPage

data class FindFiveMenteeResponse (
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: List<FindFiveMenteeItems>
) {
    data class FindFiveMenteeItems(
        val userId: String,
        val nickname: String,
        val userImg: String
    )
}