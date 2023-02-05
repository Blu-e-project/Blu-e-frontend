package com.example.blu_e.data.customercenter

import java.sql.Timestamp

data class Answer(val answerId: Int, var userId: Int, var questionId: Int,
                  var contents: String, var createdAt: Timestamp, var updateAt: Timestamp)
{
    constructor(answerId: Int) :
            this(answerId, 0, 0, "[답변 대기 중]", Timestamp(0), Timestamp(0))
}