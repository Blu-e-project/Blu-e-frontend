package com.example.blu_e.data.mainPage

data class AllProblemsResponse(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: List<Items>
) {
    data class Items(
        var problemId: Int,
        var nickname: String,
        var subject: String,
        var unit: String,
        var problem: String,
        var contents: String,
        var image: String
    )
}

