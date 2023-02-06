package com.example.blu_e.data.mentoring

import java.sql.Timestamp

data class Matching(val matchingId: Int, var userId: Int, var targetId: Int,
                    var subject: String, var createdAt: Timestamp,  var updatedAt: String)