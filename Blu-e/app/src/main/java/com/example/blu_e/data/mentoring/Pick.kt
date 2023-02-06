package com.example.blu_e.data.mentoring

import java.sql.Date
import java.sql.Timestamp

data class Pick(val pickId: Int, val userId: Int, val title: String, val contents: String?, val status: Int,
                val area: String, val mentoringMethod: String, val menteeLevel: String, val mentorCareer: String,
                val subject: String, val periodStart: Date, val periodEnd: Date, val wishGender: String,
                val role: Int, var viewCount: Int, val createdAt: Timestamp, val updatedAt: Timestamp)
