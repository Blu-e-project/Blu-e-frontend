package com.example.blu_e.data.mypage

data class ResponseReviewList(
    val isSucces:Boolean,
    val code:Int,
    val message:String,
    val result: ArrayList<MentorReviewListData>?
)