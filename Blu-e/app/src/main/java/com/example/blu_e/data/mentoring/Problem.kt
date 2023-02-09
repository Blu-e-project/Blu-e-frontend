package com.example.blu_e.data.mentoring

import java.sql.Timestamp

data class Problem(val problemId: Int, val userId: Int, val subject: String,
                val unit: String, val problem: String, val contents: String,
                val image: String, val createdAt: Timestamp, val updateAt: Timestamp)
