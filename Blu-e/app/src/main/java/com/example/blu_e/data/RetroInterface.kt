package com.example.blu_e.data

import com.example.blu_e.CreateRecruitResponse
import com.example.blu_e.LoginResponse
import com.example.blu_e.MainApplication
import com.example.blu_e.SignupResponse
import com.example.blu_e.data.accusation.Report
import com.example.blu_e.data.customercenter.QuestionResponse
import com.example.blu_e.data.mainPage.*
import com.example.blu_e.data.mentoring.Matching
import com.example.blu_e.data.mentoring.PickCommentResponse
import com.example.blu_e.data.mentoring.PickResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
<<<<<<< HEAD
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.w3c.dom.Text
=======
>>>>>>> a5938d28d64693e753b99512faf2ddd5e7a159e8
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

<<<<<<< HEAD
import retrofit2.http.*
import java.io.IOException
=======
>>>>>>> a5938d28d64693e753b99512faf2ddd5e7a159e8
import java.time.LocalDate
import retrofit2.http.GET as GET

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
                .client(okHttpClient(AppInterceptor()))
                .build()
                .create(RetroInterface::class.java)
        }
        fun okHttpClient(interceptor: AppInterceptor): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(interceptor) // okHttp에 인터셉터 추가
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }).build()
        }
        class AppInterceptor : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain) : Response = with(chain) {
                val accessToken = MainApplication.prefs.getString("blu-e-access-token", "") // ViewModel에서 지정한 key로 JWT 토큰을 가져온다.
                val newRequest = request().newBuilder()
                    .addHeader("blu-e-access-token", accessToken) // 헤더에 authorization라는 key로 JWT 를 넣어준다.
                    .build()
                proceed(newRequest)
            }
        }
    }
    //이 뒤는 이현 코드입니다!!!!!!!
    //회원 로그인
    @FormUrlEncoded
    @POST("/users/login")
    fun login(@Field("id") id:String, @Field("password") pw:String): Call<LoginResponse>

    //회원 가입
    @POST("/users/signup")
    fun signUp(@Field("userId") userId: Int, @Field("id") id: String,@Field("password") password:String, @Field("phoneNum") phoneNum: String,
               @Field("name") name : String, @Field("nickname") nickname: String,
               @Field("birth") birth: LocalDate, @Field("education") education: String,
               @Field("department") department:String?, @Field("grade") grade: Int?,
               @Field("address") address: String?, @Field("introduce") introduce: String?, @Field("role")role:Int,
               @Field("createdAt") createdAt: LocalDate, @Field("updatedAt") updatedAt: LocalDate, @Field("status") status: Int, @Field("userImg") userImg: Text?) :Call<SignupResponse>

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
    fun sendPhoneNum(@Field("phoneNum") phoneNum: String): Call<SignupResponse>

    //인증번호 보내기
    @FormUrlEncoded
    @POST("/users/verify")
    fun verifyCode(@Field("phoneNumber") phoneNum: String, @Field("verifyCode")verifyCode: String): Call<SignupResponse>

    //---------------------------------------구만이 코드----------------------------------------------
    //8 멘토 전체 조회(최근 가입한 순)
    @GET("/main/mentors")
    fun findMentors(@Header("blu-e-access-token") token: String): Call<FindMentorsResponse>

    //9 멘티 전체 조회(최근 가입한 순)
    @GET("/main/mentees")
    fun findMentees(@Header("blu-e-access-token") token: String): Call<FindMenteesResponse>

    //10 특정 멘토 정보 조회
    @GET("/main/mentors/{userId}")
    fun findMentorID(@Header("blu-e-access-token") token: String, @Path("userId") userId: Int): Call<FindMentorIdResponse>

    //11 특정 멘티 정보 조회
    @GET("/main/mentees/{userId}")
    fun findMenteeID(@Header("blu-e-access-token") token: String, @Path("userId") userId: Int): Call<FindMenteeIdResponse>

    //12 멘토 부분 조회(최신 5명)
    @GET("/main/new-mentors")
    fun findFiveMentor(@Header("blu-e-access-token") token:String): Call<FindFiveMentorResponse>

    //13 멘티 부분 조회(최신 5명)
    @GET("/main/new-mentees")
    fun findFiveMentee(@Header("blu-e-access-token") token:String): Call<FindFiveMenteeResponse>

    //15 문제 전체 조회(최신순)
    @GET("/problems")
    fun findProblems(@Header("blu-e-access-token") token: String): Call<AllProblemsResponse>

    //16 특정 문제 조회
    @GET("/problems/{problemId}")
    fun findProblemId(@Header("blu-e-access-token") token: String, @Path("problemId") problemId: Int): Call<FindProblemResponse>

    //21 멘토 구인글 부분 조회(조회수 많은 순 5개)
    @GET("/main/hot-mentors")
    fun findHotMentors(@Header("blu-e-access-token") token: String): Call<FindHotMentorResponse>

    //22 멘티 구인글 부분 조회(조회수 많은 순 5개)
    @GET("/main/hot-mentees")
    fun findHotMentees(@Header("blu-e-access-token") token: String): Call<FindHotMenteeResponse>

    //29 궁금한 문제 삭제
    @DELETE("/problems/{problemId}")
    fun deleteProblem(@Path("problemId") problemId: Int): Call<DeleteProblemResponse>

    //30 멘토 구인글 전체 조회(최신순)
    @GET("/mentoring/find-mentors")
    fun findRecruitMentors(@Header("blu-e-access-token") token: String): Call<FindRecruitMentorResponse>

    //31 멘티 구인글 전체 조회(최신순)
    @GET("/mentoring/find-mentees")
    fun findRecruitMentee(@Header("blu-e-access-token") token: String): Call<FindRecruitMenteeResponse>
}