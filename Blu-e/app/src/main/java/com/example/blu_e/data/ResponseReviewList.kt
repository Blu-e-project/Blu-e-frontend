package com.example.blu_e.data

data class ResponseReviewList(
    val isSucces:Boolean,
    val code:Int,
    val message:String,
    val result:List<reviewItem>
) data class reviewItem(
    var reviewId:Int,
    var matchingId:Int,
    var userImg:String,
    var nickname:String,
    var contents:String
)
