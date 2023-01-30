package com.example.blu_e.data

data class Matching(val matchingId: Int, var pickMenteeId: Int, var pickMenteeComId: Int,
                    var pickMentorId: Int, var pickMentorComId: Int,  var status: Int)
{
    constructor(matchingId: Int) : this(matchingId, 0, 0, 0,0,0)
}
