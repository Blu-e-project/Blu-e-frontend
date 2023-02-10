package com.example.blu_e.data.mypage

import org.w3c.dom.Text

data class ResponseReviewList(
    val isSucces:Boolean,
    val code:Int,
    val message:String,
    //val result: ArrayList<MentorReviewListData>
//)

    val result:Result
) data class Result(
    var reviewId:Int,
    var matchingId:Int,
    var userImg: Text,
    var nickname:String,
    var contents:String
)
