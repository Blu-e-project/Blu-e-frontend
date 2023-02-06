package com.example.blu_e.data.mainPage

data class FindRecruitMenteeResponse (
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: List<FindRecruitMenteeItem>
)

data class FindRecruitMenteeItem (
    val pickId: Int,
    val title: String,
    val subject: String,
    val period: String,
    val mentoringMethod: String,
    val wishGender: String
        )
