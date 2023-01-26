package com.example.blu_e.data

import com.example.blu_e.data.Answer
import java.sql.Timestamp

data class Question(var questionId: Int) {
    var userId: Int = 0
    var title: String = ""
    var contents: String = ""
    var createdAt: Timestamp = Timestamp(0)
    var updatedAt: Timestamp = Timestamp(0)
    var answer: Answer = Answer(0) //이거 추가 ?
}
