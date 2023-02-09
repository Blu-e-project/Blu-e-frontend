package com.example.blu_e.mainPage

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.blu_e.MainActivity
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.data.mainPage.FindRecruitMenteeResponse
import com.example.blu_e.data.mainPage.FindRecruitMentorResponse
import com.example.blu_e.data.mainPage.MentorData
import com.example.blu_e.data.mainPage.RetrofitRecruitMentorRVAdapter
import com.example.blu_e.databinding.FragmentHomeRecruitMentorBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRecruitMentorFragment : Fragment() {
    lateinit var viewBinding: FragmentHomeRecruitMentorBinding
    private lateinit var mContext: MainActivity
    //private val api = RetroInterface.create() //retrofit 객체


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentHomeRecruitMentorBinding.inflate(layoutInflater)
        //loadData()
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewBinding.backHome.setOnClickListener {
            mContext!!.openFragment(14)
        }
    }

    override fun onResume() {
        super.onResume()

        val list: ArrayList<MentorData> = arrayListOf()

        list.apply {
            add(MentorData("매주 화요일에 멘토링 원해요", "국어", "23.01", "23.03", "온라인", "서울 성북구"))
            add(MentorData("코딩이 너무 어려워요", "코딩", "23.01", "23.03", "온라인", "서울 금천구"))
            add(MentorData("다정하고 친절하신 선생님...", "수학", "23.01", "23.03", "온라인", "서울 은평구"))
            add(MentorData("비문학 쉽게 풀고 싶어요", "국어", "23.01", "23.03", "온라인", "서울 종로구"))
            add(MentorData("매주 목요일에 멘토링 원해요", "영어", "23.01", "23.03", "온라인", "서울 강남구"))
        }
        val mentorAdapter = MentorDataRVAdapter(list)
        val grid = GridLayoutManager(mContext, 2)

        viewBinding.recyclerViewMentor.adapter = mentorAdapter
        viewBinding.recyclerViewMentor.layoutManager = grid
    }
/*
    private fun loadData() {
        api.findMentors("eUItOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjIsImlhdCI6M..").enqueue(object :
            Callback<FindRecruitMentorResponse> {
            override fun onResponse(
                call: Call<FindRecruitMentorResponse>,
                response: Response<FindRecruitMentorResponse>
            ) {
                if (response.isSuccessful) {
                    val body = response.body() ?: return
                    if (body.code == 1000) {
                        Log.d("목록 불러오기", "성공")
                        val list = body.result as ArrayList<FindRecruitMentorResponse.FindRecruitMentorItem>

                        val mentorAdapter = RetrofitRecruitMentorRVAdapter(list)
                        val grid = GridLayoutManager(mContext, 2)

                        viewBinding.recyclerViewMentor.adapter = mentorAdapter
                        viewBinding.recyclerViewMentor.layoutManager = grid

                        mentorAdapter.setItemClickListener(object: RetrofitRecruitMentorRVAdapter.ItemClickListener{
                            override fun onClick(view: View, position: Int) {

                            }
                        })
                    }
                }
                else {
                    Log.d("새로운 멘토 리스트", "실패")
                }
            }
            override fun onFailure(call: Call<FindRecruitMenteeResponse>, t: Throwable) {
                Log.e("새로운 멘토 리스트", "failure")
            }
        })
    }*/
}