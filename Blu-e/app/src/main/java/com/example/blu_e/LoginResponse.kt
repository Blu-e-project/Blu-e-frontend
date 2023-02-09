package com.example.blu_e

data class LoginResponse(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: List<Result>
)
data class Result(
    val userId: Int,
    val jwt: String
)
