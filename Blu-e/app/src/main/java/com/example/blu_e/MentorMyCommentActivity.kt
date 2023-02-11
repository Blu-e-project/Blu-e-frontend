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

class MentorMyCommentActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMentorMyCommentBinding
    private val api = RetroInterface.create()
    lateinit var tokenStr: String
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
                val responseData = response.body()
                if (responseData != null) {
                    if(responseData.code == 1000) {
                        if(responseData.menteePickResult != null){
                            var teepick = responseData.menteePickResult
                            Log.e("전","${teepick}")
                            setMenteePickAdapter(teepick)
                            Log.e("후","${teepick}")
                        }
                        else{
                            Log.e("전","${responseData.menteePickResult}")
                            Log.d("멘티", "댓글 단 구인글이 없습니다.")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<MyMenteePickResponse>, t: Throwable) {
                
            }

        })
        //내가 쓴 멘티 구인글
        api.myMenteePick().enqueue(object : Callback<MyMenteePickResponse> {
            override fun onResponse(
                call: Call<MyMenteePickResponse>,
                response: Response<MyMenteePickResponse>
            ) {
                val responseData = response.body()
                if (responseData != null) {
                    if(responseData.code == 1000) {
                        if(responseData.menteePickResult != null){

                            var teepick = responseData.menteePickResult
                            setMenteePickAdapter(teepick)
                        }
                        else{
                            Log.e("전","${responseData.menteePickResult}")
                            Log.d("멘티", "쓴 구인글이 없습니다.")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<MyMenteePickResponse>, t: Throwable) {

            }

        })
        //내가 댓글 단 멘토 구인글 조회
        api.myMentorComPick().enqueue(object : Callback<MyMentorPickResponse> {
            override fun onResponse(
                call: Call<MyMentorPickResponse>,
                response: Response<MyMentorPickResponse>
            ) {
                val responseData = response.body()
                if (responseData != null) {
                    if(responseData.code == 1000) {
                        if(responseData.mentorPickResult != null){
                            var teepick = responseData.mentorPickResult
                            setMentorPickAdapter(teepick)
                        }
                        else{
                            Log.e("전","${responseData.mentorPickResult}")
                            Log.d("멘토", "댓글 단 단 구인글이 없습니다.")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<MyMentorPickResponse>, t: Throwable) {
            }

        })

        //내가 쓴 멘토 구인글
        api.myMentorPick().enqueue(object : Callback<MyMentorPickResponse> {
            override fun onResponse(
                call: Call<MyMentorPickResponse>,
                response: Response<MyMentorPickResponse>
            ) {

                val responseData = response.body()
                if (responseData != null) {
                    if(responseData.code == 1000) {
                        if(responseData.mentorPickResult != null){
                            var teepick = responseData.mentorPickResult
                            setMentorPickAdapter(teepick)
                        }
                        else{
                            Log.e("전","${responseData.mentorPickResult}")
                            Log.d("멘토", "댓글 단 구인글이 없습니다.")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<MyMentorPickResponse>, t: Throwable) {

            }

        })
//
//
//        //내가 쓴 질문
        api.problemByMe().enqueue(object : Callback<ProbByMeResponse> {
            override fun onResponse(
                call: Call<ProbByMeResponse>,
                response: Response<ProbByMeResponse>
            ) {
                val responseData = response.body()
                if (responseData != null) {
                    if(responseData.code == 1000) {
                        if(responseData.result != null){
                            var teepick = responseData.result
                            Log.e("어뎁터 전","${responseData.result}")
                            setAdapter(teepick)
                            Log.e("어텝터 후","${responseData.result}")
                        }
                        else{
                            Log.e("전","${responseData.result}")
                            Log.d("문제", "내가 쓴 질문이 없습니다.")
                        }
                    }
                    else{
                        Log.e("전","에러에러에러")

                    }
                }
            }

            override fun onFailure(call: Call<ProbByMeResponse>, t: Throwable) {
                Log.e("전","에러에러에러")
            }
        })
        //내가 답한 질문
        api.problemSolByMe().enqueue(object : Callback<ProbSolByMeResponse> {
            override fun onResponse(
                call: Call<ProbSolByMeResponse>,
                response: Response<ProbSolByMeResponse>
            ) {

                val responseData = response.body()
                if (responseData != null) {
                    if(responseData.code == 1000) {
                        if(responseData.solResult != null){
                            var teepick = responseData.solResult
                            setSolAdapter(teepick)
                        }
                        else{
                            Log.e("전","${responseData.solResult}")
                            Log.d("문제", "내가 답한 질문이 없습니다.")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ProbSolByMeResponse>, t: Throwable) {
            }

        })

    }

    private fun setAdapter(resultList: ArrayList<ProbByMeItem>) {
        val probAdapter = ProbByMeRVAdapter(resultList, this)
        viewBinding.rv.adapter = probAdapter
        viewBinding.rv.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        viewBinding.rv.setHasFixedSize(false)
        Log.d("데이터 수","${resultList.size}")
        probAdapter.notifyItemChanged(resultList.size)
    }

    private fun setSolAdapter(resultList: ArrayList<ProbSolByMeItem>) {
        val probSolAdapter = ProbSolByMeRVAdapter(resultList, this)
        viewBinding.rv.adapter = probSolAdapter
        viewBinding.rv.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        viewBinding.rv.setHasFixedSize(false)
        Log.d("데이터 수","${resultList.size}")
        probSolAdapter.notifyItemChanged(resultList.size)
    }

    private fun setMentorPickAdapter(resultList: ArrayList<MyMentorPickItem>) {
        val mentorPickAdapter = MyMentorPickRVAdapter(resultList, this)
        viewBinding.rvMentor.adapter = mentorPickAdapter
        viewBinding.rvMentor.layoutManager =
            GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false)
//        viewBinding.rvMentor.setHasFixedSize(false)
        Log.d("데이터 수","${resultList.size}")
        mentorPickAdapter.notifyItemChanged(resultList.size)

    }

    private fun setMenteePickAdapter(resultList: ArrayList<MyMenteePickItem>) {
        val menteePickAdapter = MyMenteePickRVAdapter(resultList)
        viewBinding.rvMentee.adapter = menteePickAdapter
        viewBinding.rvMentee.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        viewBinding.rvMentee.setHasFixedSize(false)
        Log.d("데이터 수","${resultList.size}")
        menteePickAdapter.notifyItemChanged(resultList.size)
    }
}