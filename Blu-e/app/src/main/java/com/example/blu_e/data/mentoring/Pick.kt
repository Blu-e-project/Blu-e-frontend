package com.example.blu_e.data.mentoring

import com.google.gson.annotations.SerializedName
import java.sql.Date
import java.sql.Timestamp
import java.io.Serializable
import java.time.LocalDate

data class Pick(
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("role")
    var role: Int,
    @SerializedName("pickId")
    val pickId: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("contents")
    var contents: String?,
    @SerializedName("status")
    var status: Int,
    var area: String,
    @SerializedName("mentoringMethod")
    var mentoringMethod: String?,
    @SerializedName("menteeLevel")
    var menteeLevel: String?,
    @SerializedName("mentorCareer")
    var mentorCareer: String?,
    @SerializedName("subject")
    var subject: String,
    @SerializedName("periodStart")
    var periodStart: String,
    @SerializedName("periodEnd")
    var periodEnd: String,
    @SerializedName("wishGender")
    var wishGender: String,
    @SerializedName("viewCount")
    var viewCount: Int,
    @SerializedName("createdAt")
    var createdAt: String,
    @SerializedName("updateAt")
    var updatedAt: String
) : Serializable
