package com.example.blu_e

import java.math.BigInteger
import java.sql.Timestamp

data class Answer (var answerId: Int) {
    var questionId: Int = 0
    var contents: String = "[답변 대기 중]"
    var createdAt: Timestamp = Timestamp(0)
    var updatedAt: Timestamp= Timestamp(0)
}
