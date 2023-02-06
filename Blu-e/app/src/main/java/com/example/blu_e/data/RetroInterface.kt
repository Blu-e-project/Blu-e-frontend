package com.example.blu_e.data

import com.example.blu_e.CreateRecruitResponse
import com.example.blu_e.LoginResponse
import com.example.blu_e.data.accusation.Report
import com.example.blu_e.data.customercenter.QuestionResponse
import com.example.blu_e.data.mentoring.Matching
import com.example.blu_e.data.mentoring.PickComment
import com.example.blu_e.data.mentoring.PickCommentResponse
import com.example.blu_e.data.mentoring.PickResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.w3c.dom.Text
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
    //작성한 QnA 조회
    @GET("/service/questions/{userId}")
    fun requestMyQuestions(@Header("blu-e-access-token") token: String, @Path("userId") userId: Int): Call<QuestionResponse>

    //Question 작성
    @POST("/service/questions/{userId}/writing")
    fun questionWriting(@Header("blu-e-access-token") token: String, @Query("userId") userId: Int, @Field("title") title: String, @Field("contents") contents: String): Call<QuestionResponse>

    //Question 삭제
    @DELETE("/service/questions/{userId}/writing")
    fun questionDelete(@Header("blu-e-access-token") token: String, @Path("userId") userId: Int, @Query("questionId") questionId: Int): Call<QuestionResponse>

    //회원 신고
    @POST("/service/accusations/writing")
    fun reportMember(@Header("blu-e-access-token") token: String, @Body report: Report) : Call<Report>

    //특정 멘토 구인글 조회
    @GET("/mentoring/mentors/{pickId}")
    fun requestAPostOfMento(@Header("blu-e-access-token") token: String, @Path("pickId") pickId: Int): Call<PickResponse>

    //멘토 구하는 글의 댓글 조회
    @GET("/mentoring/mentors/{pickId}/comments")
    fun requestComments(@Header("blu-e-access-token") token: String, @Path("pickId") pickId: Int): Call<PickCommentResponse>

    //멘토 구하는 글에 댓글 생성
    @POST("/mentoring/mentors/{pickId}/comments")
    fun commentWriting(@Header("blu-e-access-token") token: String, @Path("pickId") pickId: Int, @Field("contents") contents: String): Call<PickCommentResponse>

    //+ 수락 버튼 클릭 시 -> 매칭 테이블에 insert하는 부분?
    @POST
    fun requestMatching(@Query("pickMenteeId") pickMenteeId: Int, @Query("pickMentorId") pickMentorId: Int): Call<Matching>

    //멘토 구하는 글의 댓글 수정 (하려면 폼 필요)
    @PATCH("/mentoring/mentors/{pickId}/comments/{pickCommentId}")
    fun commentUpdate(@Path("pickId") pickId: Int, @Path("pickCommentId") pickCommentId: Int, @Field("contents") contents: String): Call<PickCommentResponse>

    //멘토 구하는 글의 댓글 삭제
    @DELETE("/problems/{problemId}/solutions/{solutionId}")
    fun commentDelete(@Path("pickId") pickId: Int, @Path("pickCommentId") pickCommentId: Int): Call<PickCommentResponse>

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

    //비밀번호 변경
//    @FormUrlEncoded
//    @PUT("/users/password")
//    fun pwUpdate(@Query("password") password: String, @Body user:User): Call<User>
    //멘토 구인글 작성
    @FormUrlEncoded
    @POST("/mentoring/mentors")
    fun recruitMentor(@Field("title") title: String, @Field("contents") contents: String, @Field("subject") subject:String,
                      @Field("area") area: String, @Field("mentoringMethod") mentoringMethod:String,
                        @Field("mentorCareer") mentorCareer: String, @Field("periodStart") periodStart: String, @Field("periodEnd") periodEnd:String,
                        @Field("wishGender") wishGender: String): Call<CreateRecruitResponse>

    @FormUrlEncoded
    @POST("/mentoring/mentees")
    fun recruitMentee(@Field("title") title: String, @Field("contents") contents: String, @Field("subject") subject:String,
                      @Field("area") area: String, @Field("mentoringMethod") mentoringMethod:String,
                      @Field("menteeLevel") menteeLevel: String, @Field("periodStart") periodStart: String, @Field("periodEnd") periodEnd:String,
                      @Field("wishGender") wishGender: String): Call<CreateRecruitResponse>

    //본인 인증을 위한 전화번호 보내기
    @FormUrlEncoded
    @POST("/users/send")
    fun sendPhoneNum(@Field("phoneNum") phoneNum: String): Call<LoginResponse>

    //인증번호 보내기
    @FormUrlEncoded
    @POST("/users/verify")
    fun verifyCode(@Field("phoneNumber") phoneNum: String, @Field("verifyCode")verifyCode: String): Call<LoginResponse>

    //---------------------------------------구만이 코드----------------------------------------------
    //멘토 메인화면
    //새로운 멘티가 있어요!

    //멘토를 구하고 있어요!

    //궁금한 문제가 있어요!

    //궁금한 문제가 있어요!_세부
    //멘티 구인글
}