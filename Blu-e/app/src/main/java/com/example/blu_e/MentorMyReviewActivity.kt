package com.example.blu_e

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blu_e.data.*
import com.example.blu_e.data.customercenter.Question
import com.example.blu_e.databinding.ActivityMentorMyReviewBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MentorMyReviewActivity:AppCompatActivity() {
    private val api = RetroInterface.create() //retrofit 객체
    private lateinit var review: ArrayList<MentorReviewListData>
    private lateinit var mContext: MainActivity
    lateinit var viewBinding: ActivityMentorMyReviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMentorMyReviewBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val dataList: ArrayList<MentorReviewListData> = arrayListOf()
        val dataRVAdapter = MentorReviewListRVAdatper(dataList)

        //dataList.apply {
        //    add(MentorReviewListData("임서희", "멘토님께서 친절하게 설명해주셔서 좋았어요! 기회가 된다면 또 뵙고 싶어요."))
        //    add(MentorReviewListData("고유진", "감사했습니다"))
        //    add(MentorReviewListData("진", "이해하기 쉽게 설명해주셨습니다"))
        //}
        dataRVAdapter.notifyItemInserted(2)
        viewBinding.rvDataMenteeList.adapter = dataRVAdapter
        viewBinding.rvDataMenteeList.layoutManager = LinearLayoutManager(this)

        //뒤로 가기 버튼
        viewBinding.backToMypage.setOnClickListener {
            finish()
        }

        //멘토 - 나에 대한 리뷰 목록 불러오기
        api.aboutMeReview("eUItOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjIsImlhdCI6M..").enqueue(object : Callback<ResponseReviewList> {
            override fun onResponse(
                call: Call<ResponseReviewList>,
                response: Response<ResponseReviewList>
            ) {
                if (response.isSuccessful) {
                    val body = response.body() ?: return
                    if (body.code == 1000) {
                        Log.d("나에 대한 리뷰 불러오기", "성공")

                    }
                    else if(body.code==2803){
                        Log.d("나에 대한 리뷰 불러오기", "사용자 ID 미입력")
                    }
                }
            }

            override fun onFailure(call: Call<ResponseReviewList>, t: Throwable) {
                Log.e("나에 대한 리뷰 목록", "failure")
            }
        })
    }
}