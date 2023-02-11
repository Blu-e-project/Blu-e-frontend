package com.example.blu_e

data class MyMenteePickResponse(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val menteePickResult: ArrayList<MyMenteePickItem>?
)
data class MyMenteePickItem(
    val pickId: Int,
    val area: String,
    val subject: String,
    val period: String,
    val mentoringMethod: String,
    val wishGender: String
)
