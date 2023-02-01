package com.example.blu_e.data

import java.sql.Timestamp
import java.time.LocalDate

data class User(
    val userId:Int,
    val id: String = "test",
    val password: String = "1234",
    val phoneNun:String,
    val name:String,
    val nickname: String,
    val birth: LocalDate,
    val education: String,
    val department: String,
    val grade: Int,
    val address: String,
    val introduce:String,
    val role: Int,
    val createdAt: Timestamp,
    val updatedAt: Timestamp,
    val status: Int = 1,
    val userImg: String
)
