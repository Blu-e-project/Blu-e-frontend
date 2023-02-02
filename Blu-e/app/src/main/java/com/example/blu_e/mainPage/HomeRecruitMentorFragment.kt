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
import com.example.blu_e.data.MentorData
import com.example.blu_e.databinding.FragmentHomeRecruitMentorBinding

class HomeRecruitMentorFragment : Fragment() {

    lateinit var viewBinding: FragmentHomeRecruitMentorBinding
    private lateinit var mContext: MainActivity

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

        return viewBinding.root
    }

    override fun onResume() {
        super.onResume()

        val list: ArrayList<MentorData> = arrayListOf()

        list.apply {
            add(MentorData("매주 화요일에 멘토링 원해요", "국어", "23.01 ~ 23.03", "온라인"))
            add(MentorData("코딩이 너무 어려워요", "코딩", "23.01 ~ 23.03", "온라인"))
            add(MentorData("다정하고 친절하신 선생님...", "수학", "23.01 ~ 23.03", "온라인"))
            add(MentorData("비문학 쉽게 풀고 싶어요", "국어", "23.01 ~ 23.03", "온라인"))
            add(MentorData("매주 목요일에 멘토링 원해요", "영어", "23.01 ~ 23.03", "온라인"))
        }
        val mentorAdapter = MentorDataRVAdapter(list)
        val grid = GridLayoutManager(mContext, 2)

        viewBinding.recyclerViewMentor.adapter = mentorAdapter
        viewBinding.recyclerViewMentor.layoutManager = grid
    }
}