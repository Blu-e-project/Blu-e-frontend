package com.example.blu_e

data class LoginResponse(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: LoginItem
)
data class LoginItem(
    val userId: Int,
    val jwt: String
)
