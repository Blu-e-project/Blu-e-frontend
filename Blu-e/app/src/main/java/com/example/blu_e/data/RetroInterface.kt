package com.example.blu_e.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

import retrofit2.http.*
import java.time.LocalDate

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

    //request mentoring comments read
    @GET
    fun requestAllComments(@Query("userId") userId: Int, @Query("pickMemberId") pickMemberId: Int): Call<ArrayList<PickMemberComment>>

    //request mentoring comments create
    @POST
    fun commentCreate(@Query("userId") userId: Int, @Query("pickMemberId") pickMemberId: Int, @Body pickMemberComment: PickMemberComment): Call<PickMemberComment>
    @POST
    fun requestMatching(@Query("pickMenteeId") pickMenteeId: Int, @Query("pickMentorId") pickMentorId: Int): Call<Matching>

    //request mentoring comments update
    @PUT
    fun commentUpdate(@Query("userId") userId: Int, @Query("pickMemberId") pickMemberId: Int, @Body pickMemberComment: PickMemberComment): Call<PickMemberComment>

    //request mentoring comments delete
    @DELETE
    fun commentDelete(@Query("userId") userId: Int, @Query("pickMemberId") pickMemberId: Int, @Query("pickMemberComment") pickMemberComment: Int): Call<PickMemberComment>

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
    //이 뒤는 이현 코드입니다!!!!!!!
    //회원 로그인
    @FormUrlEncoded
    @POST("/users/login")
    fun login(@Field("id") id:String, @Field("password") pw:String): Call<User>

    //회원 가입
    @POST("/users/signup")
    fun signUp1(@Field("name") name : String, @Field("nickname") nickname: String,
                @Field("birth") birth: LocalDate, @Field("education") education: String,
                @Field("department") department:String,
                @Field("address") address: String,
    @Field("introduce") introduce: String) :Call<User>


    //---------------------------------------구만이 코드----------------------------------------------
    //멘토 메인화면
    //새로운 멘티가 있어요!

    //멘토를 구하고 있어요!

    //궁금한 문제가 있어요!

    //궁금한 문제가 있어요!_세부

    //멘티 메인화면
    //새로운 멘토가 있어요!

    //멘티를 구하고 있어요!
}