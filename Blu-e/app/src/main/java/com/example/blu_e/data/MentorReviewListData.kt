package com.example.blu_e.data

import java.sql.Timestamp

data class MentorReviewListData(
    val reviewId: Int, //리뷰글 ID
    val userId: Int, //사용자 ID
    val matchingID:Int, //매칭 ID
    val contents: String, //내용
    //val status:Int, //상태
    //val createdAt: Timestamp, //생성일
    //val updatedAt: Timestamp //수정일
)
data class Question(val questionId: Int, val userId: Int, val title: String, val contents: String, val answer: String?)
{
    constructor() : this(0, 1, "", "",  "[답변 대기중]")
}
