package com.example.blu_e.data.mainPage

data class FindHotMentorResponse (
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: List<FindHotMentorItem>
) {
    data class FindHotMentorItem(
        val pickId: Int,
        val title: String,
        val subject: String,
        val period: String,
        val mentoringMethod: String,
        val wishGender: String
    )
}
