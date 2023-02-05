package com.example.blu_e.data.accusation

import java.sql.Timestamp

data class Report(val reportId: Int, var userId: Int, var targetId: Int,
                  var title: String, var contents: String, var image: String,
                  var createdAt: Timestamp, var updatedAt: Timestamp)
{
    constructor(reportId: Int) : this (reportId, 0, 0, "", "", "", Timestamp(0), Timestamp(0))
}

