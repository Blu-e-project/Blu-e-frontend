package com.example.blu_e

import org.w3c.dom.Text
import javax.security.auth.Subject

data class MyMentorPickResponse(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val MentorPickResult: List<MyMentorPickItem>
)
data class MyMentorPickItem(
    val pickId: Int,
    val area: String,
    val subject: String,
    val period: String,
    val mentoringMethod: String,
    val wishGender: String
)
