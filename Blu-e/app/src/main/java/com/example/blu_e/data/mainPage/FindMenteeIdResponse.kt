package com.example.blu_e.data.mainPage

import java.util.*

data class FindMenteeIdResponse (
        val isSuccess: Boolean,
        val code: Int,
        val message: String,
        val result: FindMenteeIdItems
) { data class FindMenteeIdItems(
        val name: String,
        val nickname: String,
        val birth: Date,
        val education: String,
        val grade: String,
        val address: String,
        val introduce: String,
        val userImg: String,
        val role: Int
        )
}