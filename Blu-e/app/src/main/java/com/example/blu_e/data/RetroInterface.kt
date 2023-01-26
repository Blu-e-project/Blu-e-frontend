package com.example.blu_e.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetroInterface {
    @GET("/service")
    //question read
    fun allFAQ(): Call<ArrayList<Question>>

//    @GET("/service/{questionId}/answer")
//    fun requestQuestionInfo(@Path("questionId") questionId: Int): Call<Question> //-> data class에 Answer 타입 넣으면 필요 없을 수도?

    //my question read
    @GET("/service/questions") //+userId
    fun requestMyQuestions(): Call<ArrayList<Question>>

    //question create
    @POST("/service/questions/writing") //+userId
    fun questionWriting(@Body question: Question): Call<Question>

    //question update
    //question delete

    //answer create
    //answer update
    //answer delete

    //service/accusations
    //service/accusations/writing
    //service/accusations/writing
    //service/accusations/attach

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