package com.example.blu_e.data

import java.sql.Timestamp

data class PickMemberComment(var id: Int) {
    val pickMemberComment
        get() = id
    var userId: Int = 0
    var pickWriterId: Int = 0
    var contents: String = ""
    var createdAt: Timestamp = Timestamp(0)
    var updatedAt: Timestamp = Timestamp(0)
    //수락 상태 / 거절 상태 / 매칭 상태 / 유저에 따라 버튼 유무 고려
}