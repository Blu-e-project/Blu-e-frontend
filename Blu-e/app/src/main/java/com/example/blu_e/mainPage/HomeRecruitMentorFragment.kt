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

    private lateinit var mentorList: ArrayList<FindRecruitMentorResponse.FindRecruitMentorItem>
    private lateinit var adapter2: RetrofitRecruitMentorRVAdapter

    companion object {
        fun newInstance(list: ArrayList<FindHotMentorResponse.FindHotMentorItem>, id: Int) =
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

    private fun loadData() {
        api.findRecruitMentors (MainApplication.prefs.getString("blu-e-access-token", "")).enqueue(object :
            Callback<FindRecruitMentorResponse> {
            override fun onResponse(
                call: Call<FindRecruitMentorResponse>,
                response: Response<FindRecruitMentorResponse>
            ) {
                if (response.isSuccessful) {
                    val body = response.body() ?: return
                    if (body.code == 1000) {
                        Log.d("목록 불러오기", "성공")
                        mentorList = body.result as ArrayList<FindRecruitMentorResponse.FindRecruitMentorItem>
                        adapter2 = RetrofitRecruitMentorRVAdapter(mentorList)

                        viewBinding.recyclerViewMentor.adapter = adapter2
                        viewBinding.recyclerViewMentor.layoutManager = GridLayoutManager(mContext, 2)

                        val intent = Intent(mContext, RequestMentoringActivity::class.java)
                        intent.putExtra("userId", "userId")
                    }
                }
                else {
                    Log.d("새로운 멘토 리스트", "실패")
                }
            }
            override fun onFailure(call: Call<FindRecruitMentorResponse>, t: Throwable) {
                Log.e("새로운 멘토 리스트", "failure")
            }
        })
    }
}