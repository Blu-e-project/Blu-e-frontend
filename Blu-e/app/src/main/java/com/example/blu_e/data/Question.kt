package com.example.blu_e.data

import java.sql.Timestamp

data class Question(val id: Int) {
    val questionId
        get() = id
    var userId: Int = 0
    var title: String = ""
    var contents: String = ""
    var createdAt: Timestamp = Timestamp(0)
    var updatedAt: Timestamp = Timestamp(0)
}
