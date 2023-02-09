package com.example.blu_e.data.mentoring

import java.sql.Date
import java.sql.Timestamp
import java.io.Serializable

data class Pick(val pickId: Int, val userId: Int, var title: String, var contents: String?, var status: Int,
                var area: String, var mentoringMethod: String, var menteeLevel: String, var mentorCareer: String,
                var subject: String, var periodStart: Date, var periodEnd: Date, var wishGender: String,
                var role: Int, var viewCount: Int, var createdAt: Timestamp, var updatedAt: Timestamp): Serializable
