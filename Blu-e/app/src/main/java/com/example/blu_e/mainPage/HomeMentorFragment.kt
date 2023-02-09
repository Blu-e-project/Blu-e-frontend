package com.example.blu_e.mainPage
//새로운 멘티가 있어요!
//궁금한 문제가 있어요!
//멘토를 구하고 있어요!
//멘티를 구해요
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blu_e.MainActivity
import com.example.blu_e.RecruitMenteeActivity
import com.example.blu_e.data.QuestionData
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.data.mainPage.*
import com.example.blu_e.databinding.FragmentHomeMentorBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeMentorFragment : Fragment() {
    private lateinit var mContext: MainActivity
    private lateinit var viewBinding: FragmentHomeMentorBinding
    private lateinit var questionAdapter: QuestionDataRVAdapter //궁금한 문제가 있어요!
    private lateinit var menteeAdapter: NewMenteeDataRVAdapter //새로운 멘티가 있어요!
    private lateinit var mentorAdapter: MentorDataRVAdapter //멘토를 구하고 있어요!

    private val api = RetroInterface.create() //retrofit 객체

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentHomeMentorBinding.inflate(layoutInflater)
        Log.e("홈", "들어왔나?")
        loadData1()
        loadData3()

        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //새로운 멘티가 있어요!
        viewBinding.btnMenteeAdd.setOnClickListener {
            mContext!!.openFragment(6)
        }
        //궁금한 문제가 있어요!
        viewBinding.btnQuestionAdd.setOnClickListener {
            mContext!!.openFragment(11)
        }
        //멘토를 구하고 있어요!
        viewBinding.btnMentorAdd.setOnClickListener {
            mContext!!.openFragment(7)
        }
        //멘티를 구해요
        viewBinding.btnMenteeInfo.setOnClickListener {
            val intent = Intent(activity, RecruitMenteeActivity::class.java)
            startActivity(intent)
        }
    }
/*
    override fun onResume() {
        super.onResume()

        //새로운 멘티가 있어요!
        val newMenteeList: ArrayList<NewMenteeData> = arrayListOf()
        Log.e("홈", "데이터 들어옴?")

        newMenteeList.apply {
            add(NewMenteeData("금림"))
            add(NewMenteeData("엘라"))
            add(NewMenteeData("맨디"))
            add(NewMenteeData("주디"))
            add(NewMenteeData("융"))
        }
        menteeAdapter = NewMenteeDataRVAdapter(newMenteeList)
        viewBinding.recyclerViewHomeNewMentee.adapter = menteeAdapter
        viewBinding.recyclerViewHomeNewMentee.layoutManager =
            LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        Log.e("홈", "어댑터 연결됨?")

        //궁금한 문제가 있어요!
        val questionList: ArrayList<QuestionData> = arrayListOf()

        questionList.apply {
            add(QuestionData("코딩", "이미지 첨부", "반복문(for문)", "조건이 왜 위와 같이 나오는지 모르겠어요!"))
            add(QuestionData("수학", "이미지 첨부", "확률과 통계", "이 문제가 왜 독립시행인지 이해가 안 돼요"))
            add(QuestionData("영어", "이미지 첨부", "to부정사", "모르겠어요.."))
            add(QuestionData("수학", "이미지 첨부", "덧셈", "모르겠어요.."))
            add(QuestionData("수학", "이미지 첨부", "미적분", "모르겠어요.."))
        }

        questionAdapter = QuestionDataRVAdapter(questionList)
        viewBinding.recyclerViewQuestion.adapter = questionAdapter
        viewBinding.recyclerViewQuestion.layoutManager =
            LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)

        //멘토를 구하고 있어요!
        val menteeList: ArrayList<MentorData> = arrayListOf()

        menteeList.apply {
            add(MentorData("매주 화요일에 멘토링 원해요", "국어", "23.01", "23.03", "온라인", "서울 성북구"))
            add(MentorData("코딩이 너무 어려워요", "코딩", "23.01", "23.03", "온라인", "서울 금천구"))
            add(MentorData("다정하고 친절하신 선생님...", "수학", "23.01", "23.03", "온라인", "서울 은평구"))
            add(MentorData("비문학 쉽게 풀고 싶어요", "국어", "23.01", "23.03", "온라인", "서울 종로구"))
            add(MentorData("매주 목요일에 멘토링 원해요", "영어", "23.01", "23.03", "온라인", "서울 강남구"))
            add(MentorData("매주 화요일에 멘토링 원해요", "국어", "23.01", "23.03", "온라인", "서울 성북구"))
            add(MentorData("코딩이 너무 어려워요", "코딩", "23.01", "23.03", "온라인", "서울 금천구"))
            add(MentorData("다정하고 친절하신 선생님...", "수학", "23.01", "23.03", "온라인", "서울 은평구"))
            add(MentorData("비문학 쉽게 풀고 싶어요", "국어", "23.01", "23.03", "온라인", "서울 종로구"))
            add(MentorData("매주 목요일에 멘토링 원해요", "영어", "23.01", "23.03", "온라인", "서울 강남구"))
        }

        mentorAdapter = MentorDataRVAdapter(menteeList)
        viewBinding.recyclerViewMentor.adapter = mentorAdapter
        viewBinding.recyclerViewMentor.layoutManager =
            LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
    }*/

    private fun loadData1() { //새로운 멘티가 있어요
        api.findFiveMentee ("eUItOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjIsImlhdCI6M..").enqueue(object :
            Callback<FindFiveMenteeResponse> {
            override fun onResponse(
                call: Call<FindFiveMenteeResponse>,
                response: Response<FindFiveMenteeResponse>
            ) {
                if (response.isSuccessful) {
                    val body = response.body() ?: return
                    if (body.code == 1000) {
                        Log.d("목록 불러오기", "성공")
                        var menteeList = body.result as ArrayList<FindFiveMenteeResponse.FindFiveMenteeItems>

                       var menteeAdapter2 = RetrofitHomeNewMenteeRVAdapter(menteeList)
                        viewBinding.recyclerViewHomeNewMentee.adapter = menteeAdapter2
                        viewBinding.recyclerViewHomeNewMentee.layoutManager =
                            LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
                    }
                }
                else {
                    Log.d("새로운 멘티 리스트", "실패")
                }
            }
            override fun onFailure(call: Call<FindFiveMenteeResponse>, t: Throwable) {
                Log.e("새로운 멘티 리스트", "failure")
            }
        })
    }

    private fun loadData3() { //멘토를 구하고 있어요
        api.findHotMentors  ("eUItOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjIsImlhdCI6M..").enqueue(object :
            Callback<FindHotMentorResponse> {
            override fun onResponse(
                call: Call<FindHotMentorResponse>,
                response: Response<FindHotMentorResponse>
            ) {
                if (response.isSuccessful) {
                    val body = response.body() ?: return
                    if (body.code == 1000) {
                        Log.d("목록 불러오기", "성공")
                        var mentorList = body.result as ArrayList<FindHotMentorResponse.FindHotMentorItem>

                        var mentorAdapter2 = RetrofitHomeRecruitMentorRVAdapter(mentorList)
                        viewBinding.recyclerViewMentor.adapter = mentorAdapter2
                        viewBinding.recyclerViewMentor.layoutManager =
                            LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
                    }
                }
                else {
                    Log.d("새로운 멘티 리스트", "실패")
                }
            }
            override fun onFailure(call: Call<FindHotMentorResponse>, t: Throwable) {
                Log.e("새로운 멘티 리스트", "failure")
            }
        })
    }
}