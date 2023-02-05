package com.example.blu_e.data.customercenter

data class FaqData(val faqId: Int, var title: String, var answer: String)
{
    constructor(faqId: Int): this(faqId, "[제목]", "[대답]")
}
