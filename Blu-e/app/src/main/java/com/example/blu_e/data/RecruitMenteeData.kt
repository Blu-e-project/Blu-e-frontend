package com.example.blu_e.data

import java.time.LocalDate

data class RecruitMenteeData(
    val region: String,
    val subject: String,
    //val periodStart:LocalDate,
    //val periodEnd: LocalDate,
    val periodStart: String,
    val periodEnd: String,
    val metoringMethod: String,
    val menteeGender: String
)
