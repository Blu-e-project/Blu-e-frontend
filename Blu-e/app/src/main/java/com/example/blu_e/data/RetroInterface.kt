package com.example.blu_e.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface RetroInterface {
    @GET("/service")
    //all faqs read
    fun allFAQ(): Call<ArrayList<Question>>

    //answer read
    @GET("/service/{questionId}/answer")
    fun requestAnswerInQuestion(@Path("questionId") questionId: Int): Call<Answer>

    //my questions read
    @GET("/service/questions")
    fun requestMyQuestions(@Query("userId") userId: Int): Call<ArrayList<Question>>

    //question create
    @POST("/service/questions/writing")
    fun questionWriting(@Query("userId") userId: Int, @Body question: Question): Call<Question>

    //question delete
    @DELETE("/service/questions/writing")
    fun questionDelete(@Query("userId") userId: Int, @Path("questionId") questionId: Int): Call<Question>

    //accusation read

    //accusation create
    @POST("/service/accusations/writing")
    fun reportMember(@Query("userId") userId: Int, @Body report: Report) : Call<Report>

//    @Multipart
//    @POST("/service/accusations/writing")
//    fun reportMember( @Query("userId") userId: Int, @Part image: MultipartBody.Part?, @Part("postData") postData: RequestBody) : Call<Report>

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