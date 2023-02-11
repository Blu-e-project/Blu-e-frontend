package com.example.blu_e.data.mainPage

data class FindFiveProblemResponse (
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: ArrayList<FindFiveProblemItems>
)
data class FindFiveProblemItems(
    var problemId: Int,
    var nickname: String,
    var subject: String,
    var unit: String,
    var problem: String,
    var contents: String,
    var image: String
)


