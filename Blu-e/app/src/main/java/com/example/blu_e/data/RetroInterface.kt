package com.example.blu_e.data

import com.example.blu_e.*
import com.example.blu_e.data.customercenter.QuestionResponse
import com.example.blu_e.data.mainPage.*
import com.example.blu_e.data.mentoring.PickCommentResponse
import com.example.blu_e.data.mentoring.PickResponse
import com.example.blu_e.data.mypage.ResponseReviewList
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.io.IOException
import java.sql.Date
import java.time.LocalDate
import retrofit2.http.GET as GET

interface RetroInterface {
    //작성한 QnA 조회
    @GET("/service/questions")
    fun requestMyQuestions(): Call<QuestionResponse>

    //특정 QnA 조회 -> 위에 통신 만으로 해결 가능해보임
    //@GET("/service/questions")
    //fun requestADetailQuestion(@Query("questionId") questionId: Int): Call<QuestionResponse>

    //Question 작성
    @FormUrlEncoded
    @POST("/service/questions/writing")
    fun questionWriting(@Field("title") title: String, @Field("contents") contents: String): Call<ResponseData>

    //Question 삭제
    @DELETE("/service/questions/writing")
    fun questionDelete(@Query("questionId") questionId: Int): Call<ResponseData>

    //회원 신고
    @FormUrlEncoded
    @POST("/service/accusations/writing")
    fun reportMember(@Field("targetId") targetId: Int, @Field("title") title: String, @Field("contents") contents: String, @Field("image") image: String) : Call<ResponseData>

    //특정 멘토 구인글 조회
    @GET("/mentoring/mentors/{pickId}")
    fun requestAPostOfMentor(@Path("pickId") pickId: Int): Call<PickResponse>
    //특정 멘티 구인글 조회
    @GET("/mentoring/mentees/{pickId}")
    fun requestAPostOfMentee(@Path("pickId") pickId: Int): Call<PickResponse>

    //멘토 구하는 글 수정 (생성 폼 사용해서)
    @PATCH("/mentoring/mentors/{pickId}")
    fun updateAPostOfMentor(@Path("pickId") pickId: Int,
                           @Field("title") title: String, @Field("contents") contents: String, @Field("subject") subject: String, @Field("area") area: String,
                           @Field("mentoringMethod") mentoringMethod: String, @Field("mentorCareer") mentorCareer: String,
                           @Field("periodStart") periodStart: String, @Field("periodEnd") periodEnd: String, @Field("wishGender") wishGender: String): Call<ResponseData>
    //멘티 구하는 글 수정 (생성 폼 사용해서)
    @PATCH("/mentoring/mentees/{pickId}")
    fun updateAPostOfMentee(@Path("pickId") pickId: Int,
                           @Field("title") title: String, @Field("contents") contents: String, @Field("subject") subject: String, @Field("area") area: String,
                           @Field("mentoringMethod") mentoringMethod: String, @Field("mentorCareer") mentorCareer: String,
                           @Field("periodStart") periodStart: String, @Field("periodEnd") periodEnd: String, @Field("wishGender") wishGender: String): Call<ResponseData>
    //멘토 구하는 글 삭제
    @DELETE("/mentoring/mentors/{pickId}")
    fun deleteAPostOfMentor(@Path("pickId") pickId: Int): Call<ResponseData>
    //멘티 구하는 글 삭제
    @DELETE("/mentoring/mentees/{pickId}")
    fun deleteAPostOfMentee(@Path("pickId") pickId: Int): Call<ResponseData>

    //멘토 구하는 글의 댓글 조회
    @GET("/mentoring/mentors/{pickId}/comments")
    fun requestMenteeComments(@Path("pickId") pickId: Int): Call<PickCommentResponse>
    //멘티 구하는 글의 댓글 조회
    @GET("/mentoring/mentees/{pickId}/comments")
    fun requestMentorComments(@Path("pickId") pickId: Int): Call<PickCommentResponse>

    //멘토 구하는 글에 댓글 생성
    @FormUrlEncoded
    @POST("/mentoring/mentors/{pickId}/comments")
    fun commentWritingAsMentee(@Path("pickId") pickId: Int, @Field("contents") contents: String): Call<ResponseData>
    //멘티 구하는 글에 댓글 생성
    @FormUrlEncoded
    @POST("/mentoring/mentees/{pickId}/comments")
    fun commentWritingAsMentor(@Path("pickId") pickId: Int, @Field("contents") contents: String): Call<ResponseData>

    //매칭 수락 버튼
    @FormUrlEncoded
    @POST("/mentoring/mentors/{pickId}/comments/{pickCommentId}/matching")
    fun requestMatching(@Path("pickId") pickId: Int,  @Path("pickCommentId") pickCommentId: Int): Call<ResponseData>

    //멘토 구하는 글의 댓글 수정 (하려면 폼 필요) - x
    @PATCH("/mentoring/mentors/{pickId}/comments/{pickCommentId}")
    fun commentUpdateAsMentee(@Path("pickId") pickId: Int, @Path("pickCommentId") pickCommentId: Int, @Field("contents") contents: String): Call<ResponseData>
    //멘티 구하는 글의 댓글 수정 (하려면 폼 필요) - x
    @PATCH("/mentoring/mentees/{pickId}/comments/{pickCommentId}")
    fun commentUpdateAsMentor(@Path("pickId") pickId: Int, @Path("pickCommentId") pickCommentId: Int, @Field("contents") contents: String): Call<ResponseData>

    //멘토 구하는 글의 댓글 삭제
    @DELETE("/mentoring/mentors/{pickId}/comments/{pickCommentId}")
    fun commentDeleteAsMentee(@Path("pickId") pickId: Int, @Path("pickCommentId") pickCommentId: Int): Call<ResponseData>
    //멘티 구하는 글의 댓글 삭제
    @DELETE("/mentoring/mentees/{pickId}/comments/{pickCommentId}")
    fun commentDeleteAsMentor(@Path("pickId") pickId: Int, @Path("pickCommentId") pickCommentId: Int): Call<ResponseData>

    //궁금한 문제 작성

    //특정 문제 조회

    //문제 해답 작성

    //문제 해답 조회

    //문제 해답 수정

    //문제 해답 제거

    companion object {
        private const val BASE_URL = "http://43.201.51.75:5000/" //"http://본인 컴퓨터 IP 주소:포트번호" //

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
    //2. 회원 로그인
    @FormUrlEncoded
    @POST("/users/login")
    fun login(@Field("id") id:String, @Field("password") pw:String): Call<LoginResponse>

    //1. 회원 가입
    @FormUrlEncoded
    @POST("/users/signup")
    fun signUp(@Field("id") id: String, @Field("password") password:String, @Field("phoneNum") phoneNum: String,
               @Field("name") name: String, @Field("nickname") nickname: String,
               @Field("birth") birth: LocalDate, @Field("education") education: String,
               @Field("department") department:String?, @Field("grade") grade: Int?,
               @Field("address") address: String?, @Field("introduce") introduce: String?, @Field("role") role:Int,
               @Field("createdAt") createdAt: LocalDate, @Field("updatedAt") updatedAt: LocalDate, @Field("status") status: Int, @Field("userImg") userImg: String?
    ) :Call<SignupResponse>

    //6. 아이디 찾기
    @FormUrlEncoded
    @POST("users/id")
    fun findId(@Field("phoneNum") phoneNum: String): Call<FindIdResponse>

    //7. 비밀번호 변경
    @FormUrlEncoded
    @PATCH("/users/password")
    fun resetPw(@Field("id") id: String, @Field("phoneNum") phoneNum: String, @Field("password") password: String, @Field("password_check") password_check:String): Call<SignupResponse>
    //34. 멘토 구인글 생성
    @FormUrlEncoded
    @POST("/mentoring/mentors")
    fun recruitMentor(@Field("title") title: String, @Field("contents") contents: String, @Field("subject") subject:String,
                      @Field("area") area: String, @Field("mentoringMethod") mentoringMethod:String,
                      @Field("mentorCareer") mentorCareer: String, @Field("periodStart") periodStart: String, @Field("periodEnd") periodEnd:String,
                      @Field("wishGender") wishGender: String): Call<CreateRecruitResponse>
    //37. 멘토 구인글 생성
    @FormUrlEncoded
    @POST("/mentoring/mentees")
    fun recruitMentee(@Field("title") title: String, @Field("contents") contents: String, @Field("subject") subject:String,
                      @Field("area") area: String, @Field("mentoringMethod") mentoringMethod:String,
                      @Field("menteeLevel") menteeLevel: String, @Field("periodStart") periodStart: String, @Field("periodEnd") periodEnd:String,
                      @Field("wishGender") wishGender: String): Call<CreateRecruitResponse>

    //50. 본인 인증을 위한 전화번호 보내기
    @FormUrlEncoded
    @POST("/users/send")
    fun sendPhoneNum(@Field("phoneNum") phoneNum: String): Call<SignupResponse>

    //51. 인증번호 보내기
    @FormUrlEncoded
    @POST("/users/verify")
    fun verifyCode(@Field("phoneNum") phoneNum: String, @Field("verifyCode")verifyCode: String): Call<SignupResponse>

    //58. 내가 질문한 문제 조회 API
    @GET("/problemByMe")
    fun problemByMe(): Call<ProbByMeResponse>

    //59. 내가 답변한 질문글 조회 API
    @GET("/problemSolByMe")
    fun problemSolByMe(): Call<ProbByMeResponse>

    //61. 내가 작성한 멘토 구인글 조회 API
    @GET("/myPage/myMentorPick")
    fun myMentorPick() : Call<MyMentorPickResponse>

    //63. 내가 댓글 단 멘토 구인글 조회 API
    @GET("/myPage/myMentorComPick")
    fun myMentorComPick() : Call<MyMentorPickResponse>

    //62. 내가 작성한 멘티 구인글 조회 API
    @GET("/myPage/myMenteePick")
    fun myMenteePick() : Call<MyMenteePickResponse>

    //64. 내가 댓글 단 멘티 구인글 조회 API
    @GET("/myPage/myMenteeComPick")
    fun myMenteeComPick() : Call<MyMenteePickResponse>

    //---------------------------------------구만이 코드----------------------------------------------
    //8 멘토 전체 조회(최근 가입한 순) NewMentor
    @GET("/main/mentors")
    fun findMentors(): Call<FindMentorsResponse>

    //9 멘티 전체 조회(최근 가입한 순) NewMentee
    @GET("/main/mentees")
    fun findMentees(): Call<FindMenteesResponse>

    //10 특정 멘토 정보 조회
    @GET("/main/mentors/{userId}")
    fun findMentorID(@Path("userId") userId: Int): Call<FindMentorIdResponse>

    //11 특정 멘티 정보 조회
    @GET("/main/mentees/{userId}")
    fun findMenteeID(@Path("userId") userId: Int): Call<FindMenteeIdResponse>

    //12 멘토 부분 조회(최신 5명) HomeMentor
    @GET("/main/new-mentors")
    fun findFiveMentor(): Call<FindFiveMentorResponse>

    //13 멘티 부분 조회(최신 5명) HomeMentee
    @GET("/main/new-mentees")
    fun findFiveMentee(): Call<FindFiveMenteeResponse>

    //15 문제 전체 조회(최신순) HomeQuestion
    @GET("/problems")
    fun findProblems(): Call<AllProblemsResponse>

    //16 특정 문제 조회
    @GET("/problems/{problemId}")
    fun findProblemId(@Path("problemId") problemId: Int): Call<FindProblemResponse>

    //21 멘토 구인글 부분 조회(조회수 많은 순 5개) HomeMentor
    @GET("/main/hot-mentors")
    fun findHotMentors(): Call<FindHotMentorResponse>

    //22 멘티 구인글 부분 조회(조회수 많은 순 5개) HomeMentee
    @GET("/main/hot-mentees")
    fun findHotMentees(): Call<FindHotMenteeResponse>

    //29 궁금한 문제 삭제
    @DELETE("/problems/{problemId}")
    fun deleteProblem(@Path("problemId") problemId: Int): Call<DeleteProblemResponse>

    //30 멘토 구인글 전체 조회(최신순) RecruitMentor
    @GET("/mentoring/find-mentors")
    fun findRecruitMentors(): Call<FindRecruitMentorResponse>

    //31 멘티 구인글 전체 조회(최신순) RecruitMentee
    @GET("/mentoring/find-mentees")
    fun findRecruitMentee(): Call<FindRecruitMenteeResponse>

    //문제 부분 조회(최신 5개)
    @GET("/problems/main")

    //-----------------------------------------------------주영-----------------------------------------
    //27 내 정보 수정
    @FormUrlEncoded
    @PATCH("/mypages/user")
    fun changeMyinfoMentor(@Field("name")name: String,@Field("nickname")nickname: String,
                           @Field("birth") birth: LocalDate, @Field("education")education: String, @Field("address")address: String, @Field("introduce")introduce: String): Call<ResponseData>

    //28 비밀번호 수정
    @FormUrlEncoded
    @PATCH("/mypages/password")
    fun changePasswdMentor(@Field("password")password: String,@Field("password2")password2:String): Call<ResponseData>

    //51 멘티->멘토 리뷰 작성
    @FormUrlEncoded
    @POST("/mentor-reviews")
    fun toMentorReview(@Field("nickname")nickname: String, @Field("subject")subject: String,@Field("contents")contents: String):Call<ResponseData>

    //52 멘토->멘티 리뷰 작성
    @FormUrlEncoded
    @POST("/mentor-reviews")
    fun toMenteeReview(@Field("nickname")nickname: String, @Field("subject")subject: String,@Field("contents")contents: String):Call<ResponseData>

    //53 특정 멘티에 대한 리뷰 조회
    @POST("/main/mentees/{userId}/review")
    fun MenteeReviewList(@Path("userId")userId: Int):Call<ResponseReviewList>

    //54 내가 쓴 리뷰 조회
    @GET("/reviews/myReview")
    fun myReviewList():Call<ResponseReviewList>

    //55 나에 대한 리뷰 조회
    @GET("/reviews/aboutMeReview")
    fun aboutMeReview():Call<ResponseReviewList>

    //56 리뷰 수정
    @FormUrlEncoded
    @PATCH("/reviews/{reviewId}")
    fun changeReview(@Path("reviewId")reviewId:Int, @Field("contents")contents: String):Call<ResponseData>

    //57 리뷰 삭제
    @DELETE("/reviews/{reviewId}")
    fun deleteReview(@Path("reviewId")reviewId:Int):Call<ResponseData>

    //65 멘토링 내역 조회
    @GET("/myPage/myMentoring")
    fun myMentoring():Call<ResponseMentoring>

    //66 특정 멘토에 대한 리뷰 조회
    @GET("/main/mentors/{userId}/review")
    fun MentorReviewList(@Path("userId")userId:Int):Call<ResponseReviewList>
}