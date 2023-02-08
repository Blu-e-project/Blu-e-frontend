package com.example.blu_e

import org.w3c.dom.Text

data class ProbSolByMeResponse(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val solResult: List<ProbSolByMeItem>
)
data class ProbSolByMeItem(
    val problemId: Int,
    val nickname: String,
    val subject: String,
    val unit: String,
    val problem: String,
    val contents: String,
    val image: Text
)

