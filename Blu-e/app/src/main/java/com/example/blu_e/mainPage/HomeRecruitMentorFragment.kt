package com.example.blu_e.mainPage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blu_e.MainActivity
import com.example.blu_e.MainApplication
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.data.mainPage.*
import com.example.blu_e.databinding.FragmentHomeRecruitMentorBinding
import com.example.blu_e.mentoring.ProfileActivity
import com.example.blu_e.mentoring.RequestMentoringActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRecruitMentorFragment : Fragment() {
    lateinit var viewBinding: FragmentHomeRecruitMentorBinding
    private lateinit var mContext: MainActivity

    private val api = RetroInterface.create() //retrofit 객체

    var mentorList: ArrayList<FindRecruitMentorItem> = arrayListOf()

    companion object {
        fun newInstance(list: ArrayList<FindHotMentorItem>, id: Int) =
            HomeRecruitMentorFragment().apply {
            arguments = Bundle().apply {
                putSerializable("list", list)
                putInt("id", id)
            }
        }
    }

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
        loadData()
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewBinding.backHome.setOnClickListener {
            mContext!!.openFragment(14)
        }
    }
/*
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
    }*/

    private fun loadData() { //새로운 멘티가 있어요
        api.findRecruitMentors ().enqueue(object : Callback<FindRecruitMentorResponse> {
            override fun onResponse(
                call: Call<FindRecruitMentorResponse>,
                response: Response<FindRecruitMentorResponse>
            ) {
                val responseData = response.body() ?: return
                if (responseData != null) {
                    if (responseData.code == 1000) {
                        if (responseData.result != null) {
                            mentorList?.addAll(responseData.result)
                            Log.e("멘토 구인글", "${mentorList}")
                            mentorList?.let { recruitMentorAdapter(it) }

                            val adapter2 = RetrofitRecruitMentorRVAdapter(mentorList, mContext)

                            adapter2.setItemClickListener(object: RetrofitRecruitMentorRVAdapter.ItemClickListener{
                                override fun onClick(view: View, position: Int) {
                                    var intent = Intent(mContext, RequestMentoringActivity::class.java)
                                    intent.putExtra("pickId", "pickId")
                                }
                            })
                        } else {
                            Log.d("멘토 구인글", "멘토 구인글이 없어용")
                        }
                    }
                }
            }
            override fun onFailure(call: Call<FindRecruitMentorResponse>, t: Throwable) {
                Log.e("멘토 구인글", "에러에러에러")
            }
        })
    }

    private fun recruitMentorAdapter(resultList: ArrayList<FindRecruitMentorItem>) {
        val adapter = RetrofitRecruitMentorRVAdapter(resultList, mContext)
        viewBinding.recyclerViewMentor.adapter = adapter
        viewBinding.recyclerViewMentor.layoutManager = GridLayoutManager(mContext, 2)
        adapter.notifyItemChanged(resultList.size)
    }
}