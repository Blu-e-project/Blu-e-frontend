package com.example.blu_e.data

import java.sql.Date
import java.sql.Timestamp

data class PickMember(val id: Int) {
    val pickMemberId
        get() = id
    var userId: Int = 0
    var title: String = ""
    var contents: String = ""
    var status: Int = 0
    var area: String = ""
    var mentoringMethod: String = ""
    var level: String = ""
    var subject: String = ""
    var periodStart: Date = Date(0)
    var peroidEnd: Date = Date(0)
    var gender: String = ""
    var createdAt: Timestamp = Timestamp(0)
    var updatedAt: Timestamp = Timestamp(0)
    var viewCount: Int = 0
}

