package com.example.blu_e.mypage

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blu_e.MainActivity
import com.example.blu_e.customercenter.QuestionDetailFragment
import com.example.blu_e.data.*
import com.example.blu_e.data.mypage.MentorReviewListData
import com.example.blu_e.data.mypage.MentorReviewListRVAdatper
import com.example.blu_e.data.mypage.ResponseReviewList
import com.example.blu_e.databinding.ActivityMentorMyReviewBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MentorMyReviewActivity:AppCompatActivity() {
    private val api = RetroInterface.create() //retrofit 객체
    private lateinit var review: MentorReviewListData
    private lateinit var rvlist: ArrayList<MentorReviewListData>
    private lateinit var mContext: MainActivity
    private lateinit var adapter: MentorReviewListRVAdatper
    lateinit var viewBinding: ActivityMentorMyReviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMentorMyReviewBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //뒤로 가기 버튼
        viewBinding.backToMypage.setOnClickListener {
            finish()
        }

        //멘토 - 내가 쓴 리뷰 목록 불러오기
        api.myReviewList()
            .enqueue(object : Callback<ResponseReviewList> {
            override fun onResponse(
                call: Call<ResponseReviewList>,
                response: Response<ResponseReviewList>
            ) {
                if (response.isSuccessful) {
                    val body = response.body() ?: return
                    if (body.code == 1000) {
                        if (body.result != null) {
                            Log.d("내가 쓴 리뷰 불러오기", "성공")
                            //데이터 불러오기
                            rvlist = body.result
                            val dataRVAdapter = MentorReviewListRVAdatper(rvlist)
                            //dataRVAdapter.notifyItemInserted(2)
                            viewBinding.rvDataMenteeList.adapter = dataRVAdapter
                            viewBinding.rvDataMenteeList.layoutManager =
                                LinearLayoutManager(this@MentorMyReviewActivity)
                            dataRVAdapter.notifyItemChanged(rvlist.size)
                        }

                            //item 클릭시 내가 쓴 리뷰 수정 또는 삭제
                            /*dataRVAdapter.setItemClickListener(object: MentorReviewListRVAdatper.ItemClickListener{
                                override fun onClick(view: View, position: Int) {
                                    //리뷰 수정 삭제 다이얼로그 띄우고 넘어가기
                                    val builder = AlertDialog.Builder(mContext)
                                    builder
                                        .setTitle("리뷰 수정 및 삭제")
                                        .setMessage("리뷰를 수정 및 삭제하겠습니까?")
                                        .setPositiveButton("수정",
                                            DialogInterface.OnClickListener { dialog, id ->
                                                api.questionDelete("", receviedQuestionId).enqueue(object: Callback<ResponseData> {
                                                    override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                                                        val body = response.body() ?: return
                                                        if(body != null) {
                                                            if(body.code == 1000) {
                                                                Log.d("질문 삭제하기", body.message)
                                                                mContext!!.openFragment(3)
                                                            }
                                                            else {
                                                                Log.d("질문 삭제하기", "실패")
                                                            }
                                                        } else {
                                                            Log.d("질문 삭제하기(바디없음)", "실패")
                                                        }
                                                    }
                                                    override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                                                        Log.d("질문 삭제하기", "실패")
                                                    }
                                                })
                                            })
                                        .setNegativeButton("삭제",
                                            DialogInterface.OnClickListener { dialog, id ->
                                                api.deleteReview(rvlist.).enqueue(object: Callback<ResponseData> {
                                                    override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                                                        val body = response.body() ?: return
                                                        if(body != null) {
                                                            if(body.code == 1000) {
                                                                Log.d("질문 삭제하기", body.message)
                                                                mContext!!.openFragment(3)
                                                            }
                                                            else {
                                                                Log.d("질문 삭제하기", "실패")
                                                            }
                                                        } else {
                                                            Log.d("질문 삭제하기(바디없음)", "실패")
                                                        }
                                                    }
                                                    override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                                                        Log.d("질문 삭제하기", "실패")
                                                    }
                                            })
                                    builder.show()

                                }*/
                        else {
                            Log.d("질문 목록 불러오기", "아직 질문이 없습니다.")
                        }
                    } else if (body.code == 2803) {
                        Log.d("내가 쓴 리뷰 불러오기", "사용자 ID 미입력")
                    }
                } else {
                    Log.d("Response: ", "null")
                }
            }
            override fun onFailure(call: Call<ResponseReviewList>, t: Throwable) {
                Log.e("내가 쓴 리뷰 목록", "failure")
            }
        })
    }
}