package com.example.blu_e.data.mentoring

import java.sql.Timestamp

data class PickComment(val pickCommentId: Int, var pickId: Int, var userId: Int,
                       var contents: String, var role: Int, var createdAt: Timestamp, var updatedAt: Timestamp)
{
    constructor() : this(0, 1, 1, "", 0, Timestamp(0), Timestamp(0))
}
