package com.example.blu_e.data.mentoring

import java.sql.Timestamp

data class Solution(val solutionId: Int, val userId: Int, val problemId: Int, var contents: String,
                    var createdAt: Timestamp, val updateAt: Timestamp)
{
    constructor() : this(0, 1, 1, "",  Timestamp(0), Timestamp(0))
}