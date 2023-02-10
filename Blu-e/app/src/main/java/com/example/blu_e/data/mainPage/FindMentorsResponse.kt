package com.example.blu_e.data.mainPage

data class FindMentorsResponse(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: FindMentorsItem
) {
    data class FindMentorsItem (
        var userId: Int,
        var nickname: String,
        var userImg: String
    )
}


