package com.example.blu_e.data.mainPage

data class FindMentorsResponse(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: ArrayList<FindMentorItem>
)
    data class FindMentorItem (
        var userId: Int,
        var nickname: String,
        var userImg: String
    )



