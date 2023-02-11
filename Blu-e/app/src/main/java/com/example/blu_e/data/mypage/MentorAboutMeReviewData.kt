package com.example.blu_e.data.mypage

import org.w3c.dom.Text

data class MentorAboutMeReviewData (
    var reviewId: Int, //리뷰글 ID
    var matchingID:Int, //매칭 ID
    var userImg: Text,
    var nickname: String, //멘티닉네임
    var contents: String //내용
)