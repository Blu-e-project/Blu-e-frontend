package com.example.blu_e.data.mentoring

import java.sql.Timestamp

data class PickMemberComment(val pickMemberComment: Int, var userId: Int, var pickWriterId: Int,
                             var contents: String, var createdAt: Timestamp, var updatedAt: Timestamp)
{
constructor(pickMemberComment: Int) : this (pickMemberComment, 0, 0,"", Timestamp(0), Timestamp(0))
    /*//수락 상태 / 거절 상태 / 매칭 상태 / 유저에 따라 버튼 유무 고려*/
}