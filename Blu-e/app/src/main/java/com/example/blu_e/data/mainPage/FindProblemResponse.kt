package com.example.blu_e.data.mainPage

import java.util.*

data class FindProblemResponse (
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: List<FindProblemItems>
) {
    data class FindProblemItems(
        val nickname: String,
        val subject: String,
        val unit: String,
        val problem: String,
        val contents: String,
        val image: String
    )
}


