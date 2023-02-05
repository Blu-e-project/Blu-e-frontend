package com.example.blu_e.data.customercenter

import java.sql.Timestamp

data class Question(val questionId: Int, var userId: Int, var title: String,
                    var contents: String, var createdAt: Timestamp, var updatedAt: Timestamp)
{
    constructor(questionId: Int): this(questionId, 0, "[예시]", "[예시]", Timestamp(0), Timestamp(0))
}
