package com.example.blu_e.data.mainPage

data class FindHotMenteeResponse (
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: ArrayList<FindHotMenteeItem>?
)
data class FindHotMenteeItem(
    val userId: Int,
    val pickId: Int,
    val title: String,
    val subject: String,
    val period: String,
    val mentoringMethod: String,
    val wishGender: String,
    val role: Int
)


