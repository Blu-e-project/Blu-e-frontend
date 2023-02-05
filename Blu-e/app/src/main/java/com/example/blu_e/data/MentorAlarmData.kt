
package com.example.blu_e.data

/*
class MentorAlarmData (
    val mentee: String,
    val matching: String,
    val matching2: String
)*/

data class MentorAlarmData (val alarmId: Int, var mentee: String, var mathcing: String, var mathcing2: String)
{
    constructor(alarmId: Int): this(alarmId, "[멘티이름]", "[알람종류]","[성공여부]")
}