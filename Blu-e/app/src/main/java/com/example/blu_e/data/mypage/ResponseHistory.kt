package com.example.blu_e.data.mypage

data class ResponseHistory(
    val isSucces:Boolean,
    val code:Int,
    val message:String,
    val result:ArrayList<MentorHistoryData>?
)