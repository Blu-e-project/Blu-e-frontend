package com.example.blu_e.data.customercenter

data class Question(val questionId: Int, val userId: Int, val title: String, val contents: String, val answer: String?)
{
    constructor() : this(0, 1, "", "",  "[답변 대기중]")
}
