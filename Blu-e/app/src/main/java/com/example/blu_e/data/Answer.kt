package com.example.blu_e.data

import java.sql.Timestamp

data class Answer(var id: Int) {
    val answerId
        get() = id
    var userId: Int = 0
    var questionId: Int = 0
    var contents: String = "[답변 대기 중]"
    var createdAt: Timestamp = Timestamp(0)
    var updatedAt: Timestamp= Timestamp(0)
}
