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
import com.example.blu_e.RecruitMentorActivity
import com.example.blu_e.data.mainPage.MenteeData
import com.example.blu_e.data.mainPage.NewMentorData
import com.example.blu_e.databinding.FragmentHomeMenteeBinding

class HomeMenteeFragment : Fragment() {
    private lateinit var mContext: MainActivity
    private lateinit var viewBinding: FragmentHomeMenteeBinding
    private lateinit var mentorAdapter: NewMentorDataRVAdapter //새로운 멘토가 있어요!
    private lateinit var recruitMenteeAdapter: MenteeDataRVAdapter //멘티를 구하고 있어요!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentHomeMenteeBinding.inflate(layoutInflater)

        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //새로운 멘토가 있어요!
        viewBinding.btnMentorAdd.setOnClickListener {
            mContext!!.openFragment(9)
        }
        //멘티를 구하고 있어요!
        viewBinding.btnRecruitMentee.setOnClickListener {
            mContext!!.openFragment(10)
        }
        //궁금한 문제가 있어요!
        viewBinding.btnAddProblem.setOnClickListener {
            mContext!!.openFragment(11)
        }
        //멘토를 구하고 있어요!
        viewBinding.btnMentorInfo.setOnClickListener {
            val intent = Intent(activity, RecruitMentorActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onResume() {
        super.onResume()

        //새로운 멘토가 있어요!
        val newMentorList: ArrayList<NewMentorData> = arrayListOf()
        Log.e("홈", "데이터 들어옴?")

        newMentorList.apply {
            add(NewMentorData("금림"))
            add(NewMentorData("엘라"))
            add(NewMentorData("맨디"))
            add(NewMentorData("주디"))
            add(NewMentorData("융"))
            add(NewMentorData("시니"))
            add(NewMentorData("쫑"))
            add(NewMentorData("제이드"))
            add(NewMentorData("니케"))
            add(NewMentorData("그리드"))
        }
        mentorAdapter = NewMentorDataRVAdapter(newMentorList)
        viewBinding.recyclerViewNewMentee.adapter = mentorAdapter
        viewBinding.recyclerViewNewMentee.layoutManager =
            LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        Log.e("홈", "어댑터 연결됨?")

        //멘티를 구하고 있어요!
        val menteeList: ArrayList<MenteeData> = arrayListOf()

        menteeList.apply {
            add(MenteeData("매주 화요일에 멘토링 원해요", "국어", "23.01", "23.03", "온라인", "서울 성북구"))
            add(MenteeData("코딩이 너무 어려워요", "코딩", "23.01", "23.03", "온라인", "서울 금천구"))
            add(MenteeData("다정하고 친절하신 선생님...", "수학", "23.01", "23.03", "온라인", "서울 은평구"))
            add(MenteeData("비문학 쉽게 풀고 싶어요", "국어", "23.01", "23.03", "온라인", "서울 종로구"))
            add(MenteeData("매주 목요일에 멘토링 원해요", "영어", "23.01", "23.03", "온라인", "서울 강남구"))
            add(MenteeData("매주 화요일에 멘토링 원해요", "국어", "23.01", "23.03", "온라인", "서울 성북구"))
            add(MenteeData("코딩이 너무 어려워요", "코딩", "23.01", "23.03", "온라인", "서울 금천구"))
            add(MenteeData("다정하고 친절하신 선생님...", "수학", "23.01", "23.03", "온라인", "서울 은평구"))
            add(MenteeData("비문학 쉽게 풀고 싶어요", "국어", "23.01", "23.03", "온라인", "서울 종로구"))
            add(MenteeData("매주 목요일에 멘토링 원해요", "영어", "23.01", "23.03", "온라인", "서울 강남구"))
        }
        recruitMenteeAdapter = MenteeDataRVAdapter(menteeList)
        viewBinding.recyclerViewMentee.adapter = recruitMenteeAdapter
        viewBinding.recyclerViewMentee.layoutManager =
            LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
    }
}