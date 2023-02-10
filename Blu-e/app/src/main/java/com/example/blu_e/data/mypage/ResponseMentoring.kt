package com.example.blu_e.data

data class ResponseMentoring(
    val isSucces:Boolean,
    val code:Int,
    val message:String,
    val result:List<mentoringItem>
) data class mentoringItem(
    var userId:Int,
    var nickname:String,
    var state:String,
    var userImg:String
)
