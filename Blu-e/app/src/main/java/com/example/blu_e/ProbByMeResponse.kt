package com.example.blu_e

import org.w3c.dom.Text

data class ProbByMeResponse(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: List<ProbByMeItem>
)
data class ProbByMeItem(
    val problemId: Int,
    val nickname: String,
    val subject: String,
    val unit: String,
    val problem: String,
    val contents: String,
    val image: Text
)
