package com.example.blu_e.mypage

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blu_e.MainActivity
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.data.mypage.MentorAboutMeReviewRVAdapter
import com.example.blu_e.data.mypage.MentorReviewListData
import com.example.blu_e.data.mypage.MentorReviewListRVAdatper
import com.example.blu_e.data.mypage.ResponseReviewList
import com.example.blu_e.databinding.ActivityMentorAboutMeReviewBinding
import com.example.blu_e.databinding.ActivityMentorMyReviewBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MentorAboutMeReviewActivity : AppCompatActivity(){
    private val api = RetroInterface.create() //retrofit 객체
    private lateinit var review: MentorReviewListData
    private lateinit var rvlist: ArrayList<MentorReviewListData>
    private lateinit var mContext: MainActivity
    private lateinit var adapter: MentorReviewListRVAdatper
    lateinit var viewBinding: ActivityMentorAboutMeReviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMentorAboutMeReviewBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //val dataList: ArrayList<MentorReviewListData> = arrayListOf()
        //val dataRVAdapter = MentorReviewListRVAdatper(dataList)

        //dataList.apply {
        //    add(MentorReviewListData("임서희", "멘토님께서 친절하게 설명해주셔서 좋았어요! 기회가 된다면 또 뵙고 싶어요."))
        //    add(MentorReviewListData("고유진", "감사했습니다"))
        //    add(MentorReviewListData("진", "이해하기 쉽게 설명해주셨습니다"))
        //}
        //dataRVAdapter.notifyItemInserted(2)
        //viewBinding.rvDataMenteeList.adapter = dataRVAdapter
        //viewBinding.rvDataMenteeList.layoutManager = LinearLayoutManager(this)

        //뒤로 가기 버튼
        viewBinding.backToMypageAboutMe.setOnClickListener {
            finish()
        }

        //멘토 - 내가 쓴 리뷰 목록 불러오기
        api.aboutMeReview()
            .enqueue(object : Callback<ResponseReviewList> {
                override fun onResponse(
                    call: Call<ResponseReviewList>,
                    response: Response<ResponseReviewList>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body() ?: return
                        if (body.code == 1000) {
                            if (body.result != null) {
                                Log.d("나에 대한 리뷰 불러오기", "성공")
                                val dataList: ArrayList<MentorReviewListData> = arrayListOf()
                                val dataRVAdapter = MentorReviewListRVAdatper(dataList)
                                dataList.apply {
                                    add(MentorReviewListData(body.result.reviewId,body.result.matchingId,
                                    body.result.userImg,body.result.nickname,body.result.contents))
                                }

                                //rvlist = body.result
                                //val dataRVAdapter = MentorAboutMeReviewRVAdapter(rvlist)
                                dataRVAdapter.notifyItemInserted(2)
                                viewBinding.rvDataMenteeList.adapter = dataRVAdapter
                                viewBinding.rvDataMenteeList.layoutManager = LinearLayoutManager(this@MentorAboutMeReviewActivity)
                                //dataRVAdapter.notifyItemChanged(rvlist.size)







                                //adapter2 = QuestionAdapter(qs)
                                //viewBinding.recyclerViewQa.adapter = adapter2
                                //viewBinding.recyclerViewQa.layoutManager = LinearLayoutManager(mContext)
                                //adapter2.notifyItemChanged(qs.size)

                                //review.reviewId=body.result.reviewId
                                //review.matchingID=body.result.matchingId
                                //review.userImg=body.result.userImg
                                //review.nickname=body.result.nickname
                                //review.contents=body.result.contents

                            } else {
                                Log.d("질문 목록 불러오기", "아직 질문이 없습니다.")
                            }

                        } else if (body.code == 2803) {
                            Log.d("나에 대한 리뷰 불러오기", "사용자 ID 미입력")
                        }
                    } else {
                        Log.d("Response: ", "null")
                    }
                }
                override fun onFailure(call: Call<ResponseReviewList>, t: Throwable) {
                    Log.e("나에 대한 리뷰 목록", "failure")
                }
            })
    }

}