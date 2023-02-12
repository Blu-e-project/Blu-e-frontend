package com.example.blu_e

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.databinding.ActivityMentorMyCommentBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPostCommentActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMentorMyCommentBinding
    private val api = RetroInterface.create()
    lateinit var tokenStr: String
    var teeList: ArrayList<MyMenteePickItem>? = null
    var torList: ArrayList<MyMentorPickItem>? = null
    var probList: ArrayList<ProbByMeItem>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMentorMyCommentBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        tokenStr = MainApplication.prefs.getString("blu-e-access-token", "")


        //내기 댓글 단 멘티 구인글 조회
        api.myMenteeComPick().enqueue(object : Callback<MyMenteePickResponse> {
            override fun onResponse(
                call: Call<MyMenteePickResponse>,
                response: Response<MyMenteePickResponse>
            ) {
                val responseData = response.body()?: return
                if (responseData != null) {
                    if(responseData.code == 1000) {
                        if(responseData.result != null){
                            teeList = responseData.result
                            Log.e("멘티", "${teeList}")
                        }
                        else{
                            Log.d("멘티", "아직 댓글 단 구인글이 없습니다.")
                        }
                    }
                }
                else{
                    Log.d("Response", "null")
                }
            }
            override fun onFailure(call: Call<MyMenteePickResponse>, t: Throwable) {
                Log.d("Response", "불러오기 실패!")
            }

        })
        //내가 쓴 멘티 구인글
        api.myMenteePick().enqueue(object : Callback<MyMenteePickResponse> {
            override fun onResponse(
                call: Call<MyMenteePickResponse>,
                response: Response<MyMenteePickResponse>
            ) {
                val responseData = response.body()?: return
                if (responseData != null) {
                    if(responseData.code == 1000) {
                        if(responseData.result != null){
                            teeList?.addAll(responseData.result)
                            Log.e("멘티", "${teeList}")
                            teeList?.let { setMenteePickAdapter(it) }
                        }
                        else{
                            Log.d("멘티", "아직 작성한 구인글이 없습니다.")
                        }
                    }
                }
                else{
                    Log.d("Response: ", "null")
                }
            }

            override fun onFailure(call: Call<MyMenteePickResponse>, t: Throwable) {
                Log.d("Response", "불러오기 실패!")
            }

        })
        //내가 댓글 단 멘토 구인글 조회
        api.myMentorComPick().enqueue(object : Callback<MyMentorPickResponse> {
            override fun onResponse(
                call: Call<MyMentorPickResponse>,
                response: Response<MyMentorPickResponse>
            ) {
                val responseData = response.body()?: return
                if (responseData != null) {
                    if(responseData.code == 1000) {
                        if(responseData.result != null){
                            torList = responseData.result
                            Log.e("멘토", "${torList}")
                        }
                        else{
                            Log.d("멘토", "댓글 단 단 구인글이 없습니다.")
                        }
                    }
                }
                else{
                    Log.d("Response", "null")
                }
            }
            override fun onFailure(call: Call<MyMentorPickResponse>, t: Throwable) {
                Log.d("Response", "불러오기 실패!")
            }
        })
//        내가 쓴 멘토 구인글
        api.myMentorPick().enqueue(object : Callback<MyMentorPickResponse> {
            override fun onResponse(
                call: Call<MyMentorPickResponse>,
                response: Response<MyMentorPickResponse>
            ) {

                val responseData = response.body()?: return
                if (responseData != null) {
                    if(responseData.code == 1000) {
                        if(responseData.result != null){
                            torList?.addAll(responseData.result)
                            Log.e("멘토", "${torList}")
                            torList?.let { setMentorPickAdapter(it) }
                        }
                        else{
                            Log.d("멘토", "댓글 단 구인글이 없습니다.")
                        }
                    }
                }
                else{
                    Log.d("Response", "null")
                }
            }
            override fun onFailure(call: Call<MyMentorPickResponse>, t: Throwable) {
                Log.d("Response", "불러오기 실패!")
            }
        })
//
////        내가 쓴 질문
        api.problemByMe().enqueue(object : Callback<ProbByMeResponse> {
            override fun onResponse(
                call: Call<ProbByMeResponse>,
                response: Response<ProbByMeResponse>
            ) {
                val responseData = response.body()?: return
                if (responseData != null) {
                    if(responseData.code == 1000) {
                        if(responseData.result != null){
                            probList = responseData.result
                            Log.e("문제", "${probList}")
                        }
                        else{
                            Log.d("문제", "내가 답한 질문이 없습니다.")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ProbByMeResponse>, t: Throwable) {
                Log.e("내가 답한 질문","에러에러에러")
            }
        })
        //내가 답한 질문
        api.problemSolByMe().enqueue(object : Callback<ProbByMeResponse> {
            override fun onResponse(
                call: Call<ProbByMeResponse>,
                response: Response<ProbByMeResponse>
            ) {
                val responseData = response.body()?: return
                if (responseData != null) {
                    if(responseData.code == 1000) {
                        if(responseData.result != null){
                            probList?.addAll(responseData.result)
                            Log.e("문제", "${probList}")
                            probList?.let { setProbAdapter(it) }
                        }
                        else{
                            Log.d("문제", "내가 답한 질문이 없습니다.")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ProbByMeResponse>, t: Throwable) {
                Log.e("내가 답한 질문","에러에러에러")
            }

        })

    }

    private fun setProbAdapter(resultList: ArrayList<ProbByMeItem>) {
        val probAdapter = ProbByMeRVAdapter(resultList)
        viewBinding.rv.adapter = probAdapter
        viewBinding.rv.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        probAdapter.notifyItemChanged(resultList.size)
    }

    private fun setMentorPickAdapter(resultList: ArrayList<MyMentorPickItem>) {
        val mentorPickAdapter = MyMentorPickRVAdapter(resultList, this)
        viewBinding.rvMentor.adapter = mentorPickAdapter
        viewBinding.rvMentor.layoutManager =
            GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false)
        mentorPickAdapter.notifyItemChanged(resultList.size)

    }

    private fun setMenteePickAdapter(resultList: ArrayList<MyMenteePickItem>) {
        val menteePickAdapter = MyMenteePickRVAdapter(resultList, this)
        viewBinding.rvMentee.adapter = menteePickAdapter
        viewBinding.rvMentee.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        menteePickAdapter.notifyItemChanged(resultList.size)
    }
}