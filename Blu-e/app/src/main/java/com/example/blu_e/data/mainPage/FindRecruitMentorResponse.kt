package com.example.blu_e.data.mainPage

class FindRecruitMentorResponse (
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: FindRecruitMentorItem
) {
    data class FindRecruitMentorItem(
        val pickId: Int,
        val title: String,
        val subject: String,
        val period: String,
        val mentoringMethod: String,
        val wishGender: String
    )
}