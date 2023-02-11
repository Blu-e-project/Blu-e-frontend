package com.example.blu_e.data.mypage


data class MentorReviewListData(
    var reviewId: Int, //리뷰글 ID
    var matchingID:Int, //매칭 ID
    var userImg:String,
    var nickname: String, //멘티닉네임
    var contents: String? //내용
)