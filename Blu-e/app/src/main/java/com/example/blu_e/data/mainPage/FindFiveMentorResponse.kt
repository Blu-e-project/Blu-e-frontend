package com.example.blu_e.data.mainPage

data class FindFiveMentorResponse (
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: List<FindFiveMentorItems>
)

data class FindFiveMentorItems(
    val userId: String,
    val nickname: String,
    val userImg: String
)