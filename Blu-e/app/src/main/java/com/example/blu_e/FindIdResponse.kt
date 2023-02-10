package com.example.blu_e

data class FindIdResponse(
    val isSuccess: String,
    val code: Int,
    val message: String,
    val result: Item
)
data class Item(
    val id: String
)
