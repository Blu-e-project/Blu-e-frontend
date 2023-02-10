package com.example.blu_e.data.mypage

import org.w3c.dom.Text

data class MentorReviewListData(
    var reviewId: Int, //리뷰글 ID
    var matchingID:Int, //매칭 ID
    var userImg:Text,
    var nickname: String, //멘티닉네임
    var contents: String //내용
)
/*
data class Question(val questionId: Int, val userId: Int, val title: String, val contents: String, val answer: String?)
{
    constructor() : this(0, 1, "", "",  "[답변 대기중]")
}
*/