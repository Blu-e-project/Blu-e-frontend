package com.example.blu_e.data.mainPage

import java.util.*

data class FindMentorIdResponse (
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: FindMentorIdItems
) {
    data class FindMentorIdItems(
        val name: String,
        val nickname: String,
        val birth: Date,
        val education: String,
        val department: String,
        val address: String,
        val introduce: String,
        val userImg: String
        )
}