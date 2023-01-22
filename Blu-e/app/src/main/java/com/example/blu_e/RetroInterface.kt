package com.example.blu_e

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RetroInterface {
    @GET("/service")
    fun allFAQ(): Call<ArrayList<Question>>

//    @GET("/service/{questionId}/answer")
//    fun requestQuestionInfo(@Path("questionId") questionId: Int): Call<Question> //-> data class에 Answer 타입 넣으면 필요 없을 수도?

    @GET("/service/questions") //userId, questionId
    fun requestMyQuestions(): Call<ArrayList<Question>>

    @POST("/service/questions/writing") //userId
    fun questionWriting(): Call<Question>

    companion object {
        private const val BASE_URL = "http://" //"http://본인 컴퓨터 IP 주소:포트번호" //

        fun create(): RetroInterface {
            val gson : Gson = GsonBuilder().setLenient().create()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(RetroInterface::class.java)
        }
    }
}