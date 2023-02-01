package com.example.blu_e.mainPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.blu_e.data.QuestionData
import com.example.blu_e.databinding.FragmentHomeRecruitMenteeBinding

class HomeRecruitMenteeFragment : Fragment() {
    lateinit var viewBinding: FragmentHomeRecruitMenteeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentHomeRecruitMenteeBinding.inflate(layoutInflater)

        val list: ArrayList<QuestionData> = arrayListOf()

        list.apply {
            add(QuestionData("국어", "이미지 첨부", "문학", "해석이 어려워요."))
            add(QuestionData("코딩", "이미지 첨부", "반복문(for문)", "조건이 왜 위와 같이 나오는지 모르겠어요!"))
            add(QuestionData("수학", "이미지 첨부", "확률과 통계", "이 문제가 왜 독립시행인지 이해가 안 돼요."))
            add(QuestionData("국어", "이미지 첨부", "문학", "해석이 어려워요."))
            add(QuestionData("영어", "이미지 첨부", "2단원", "해석이 어려워요."))
            add(QuestionData("국어", "이미지 첨부", "문학", "해석이 어려워요."))
            add(QuestionData("코딩", "이미지 첨부", "반복문(for문)", "조건이 왜 위와 같이 나오는지 모르겠어요!"))
            add(QuestionData("수학", "이미지 첨부", "확률과 통계", "이 문제가 왜 독립시행인지 이해가 안 돼요."))
            add(QuestionData("국어", "이미지 첨부", "문학", "해석이 어려워요."))
            add(QuestionData("영어", "이미지 첨부", "2단원", "해석이 어려워요."))
        }
        val adapter = QuestionDataRVAdapter(list)
        val grid = GridLayoutManager(context, 2)

        viewBinding.recyclerViewMentee.adapter = adapter
        viewBinding.recyclerViewMentee.layoutManager = grid

        return viewBinding.root
    }
}

