package com.example.blu_e.data.mentoring

import java.sql.Date
import java.sql.Timestamp

data class PickMember(val  pickMemberId: Int, var userId: Int, var title: String, var contents: String,
                      var status: Int, var area: String, var mentoringMethod: String, var level: String,
                      var subject: String, var periodStart: Date, var peroidEnd: Date, var gender: String,
                      var createdAt: Timestamp, var updatedAt: Timestamp, var viewCount: Int)
{
    constructor(pickMemberId: Int) : this (pickMemberId, 0, "", "", 0,
    "", "", "", "", Date(0), Date(0), "", Timestamp(0),
    Timestamp(0), 0)
}

