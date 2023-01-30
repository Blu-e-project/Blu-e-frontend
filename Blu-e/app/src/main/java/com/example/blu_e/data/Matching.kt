package com.example.blu_e.data

data class Matching(var id: Int) {
    val matchingId
        get() = id
    var pickMenteeId: Int = 0
    var pickMenteeComId: Int = 0
    var pickMentorId: Int = 0
    var pickMentorComId: Int = 0
    var status: Int = 0
}
