package com.example.blu_e.data.mainPage

data class FindMenteesResponse (
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: ArrayList<FindMenteeItem>?
)
    data class FindMenteeItem(
        val userId: Int,
        val nickname: String,
        val userImg: String,
        val role: Int
    )