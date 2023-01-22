package com.example.blu_e

import java.math.BigInteger
import java.sql.Timestamp
import java.io.Serializable
import java.lang.reflect.Constructor

data class Question(var questionId: Int) {
    var userId: Int = 0
    var title: String = ""
    var contents: String = ""
    var createdAt: Timestamp = Timestamp(0)
    var updatedAt: Timestamp = Timestamp(0)
    var answer: Answer = Answer(0) //이거 추가 ?
}
