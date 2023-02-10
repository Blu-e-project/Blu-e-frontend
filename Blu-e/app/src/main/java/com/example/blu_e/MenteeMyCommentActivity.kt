package com.example.blu_e

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.databinding.ActivityMenteeMyCommentBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenteeMyCommentActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMenteeMyCommentBinding
    private val api = RetroInterface.create()
    lateinit var tokenStr: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMenteeMyCommentBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        tokenStr = MainApplication.prefs.getString("blu-e-access-token","")


        //내기 댓글 단 멘티 구인글 조회
        api.myMenteeComPick().enqueue(object : Callback<MyMenteePickResponse>{
            override fun onResponse(
                call: Call<MyMenteePickResponse>,
                response: Response<MyMenteePickResponse>
            ) {
                val responseData = response.body()
                responseData?.let {
                    setMenteePickAdapter(it.MenteePickResult as ArrayList<MyMenteePickItem>)
                }
            }

            override fun onFailure(call: Call<MyMenteePickResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        //내가 쓴 멘티 구인글
        api.myMenteePick().enqueue(object : Callback<MyMenteePickResponse>{
            override fun onResponse(
                call: Call<MyMenteePickResponse>,
                response: Response<MyMenteePickResponse>
            ) {
                val responseData = response.body()
                responseData?.let {
                    setMenteePickAdapter(it.MenteePickResult as ArrayList<MyMenteePickItem>)
                }
            }

            override fun onFailure(call: Call<MyMenteePickResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        //내가 댓글 단 멘토 구인글 조회
        api.myMentorComPick().enqueue(object : Callback<MyMentorPickResponse>{
            override fun onResponse(
                call: Call<MyMentorPickResponse>,
                response: Response<MyMentorPickResponse>
            ) {
                val responseData = response.body()
                responseData?.let {
                    setMentorPickAdapter(it.MentorPickResult as ArrayList<MyMentorPickItem>)
                }
            }

            override fun onFailure(call: Call<MyMentorPickResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

        //내가 쓴 멘토 구인글
        api.myMentorPick().enqueue(object : Callback<MyMentorPickResponse>{
            override fun onResponse(
                call: Call<MyMentorPickResponse>,
                response: Response<MyMentorPickResponse>
            ) {
                val responseData = response.body()
                responseData?.let {
                    setMentorPickAdapter(it.MentorPickResult as ArrayList<MyMentorPickItem>)
                }
            }

            override fun onFailure(call: Call<MyMentorPickResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })


        //내가 쓴 질문
        api.problemByMe().enqueue(object : Callback<ProbByMeResponse>{
            override fun onResponse(
                call: Call<ProbByMeResponse>,
                response: Response<ProbByMeResponse>
            ) {
                val responseData = response.body()
                responseData?.let {
                    setAdapter(it.result as ArrayList<ProbByMeItem>)
                }
            }

            override fun onFailure(call: Call<ProbByMeResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        //내가 답한 질문
        api.problemSolByMe().enqueue(object : Callback<ProbSolByMeResponse>{
            override fun onResponse(
                call: Call<ProbSolByMeResponse>,
                response: Response<ProbSolByMeResponse>
            ) {
                val responseData = response.body()
                responseData?.let {
                    setSolAdapter(it.solResult as ArrayList<ProbSolByMeItem>)
                }
            }

            override fun onFailure(call: Call<ProbSolByMeResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }
    private fun setAdapter(resultList: ArrayList<ProbByMeItem>){
        val probAdapter = ProbByMeRVAdapter(resultList, this)
        viewBinding.rv.adapter = probAdapter
        viewBinding.rv.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        viewBinding.rv.setHasFixedSize(false)
    }

    private fun setSolAdapter(resultList: ArrayList<ProbSolByMeItem>){
        val probSolAdapter = ProbSolByMeRVAdapter(resultList, this)
        viewBinding.rv.adapter = probSolAdapter
        viewBinding.rv.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        viewBinding.rv.setHasFixedSize(false)
    }

    private fun setMentorPickAdapter(resultList: ArrayList<MyMentorPickItem>){
        val mentorPickAdapter = MyMentorPickRVAdapter(resultList, this)
        viewBinding.rvMentor.adapter = mentorPickAdapter
        viewBinding.rvMentor.layoutManager =
            GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false)
        viewBinding.rvMentor.setHasFixedSize(false)

    }

    private fun setMenteePickAdapter(resultList: ArrayList<MyMenteePickItem>){
        val menteePickAdapter = MyMenteePickRVAdapter(resultList, this)
        viewBinding.rvMentee.adapter = menteePickAdapter
        viewBinding.rvMentee.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        viewBinding.rvMentee.setHasFixedSize(false)

    }
}