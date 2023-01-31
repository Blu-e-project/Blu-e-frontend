package com.example.blu_e.mainPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.blu_e.databinding.ActivityHomeRecruitMenteeBinding

class HomeRecruitMenteeActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityHomeRecruitMenteeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityHomeRecruitMenteeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val list = ArrayList<MenteeData>()
        list.add(MenteeData("국어", "이미지 첨부", "문학", "해석이 어려워요."))
        list.add(MenteeData("코딩", "이미지 첨부", "반복문(for문)", "조건이 왜 위와 같이 나오는지 모르겠어요!"))
        list.add(MenteeData("수학", "이미지 첨부", "확률과 통계", "이 문제가 왜 독립시행인지 이해가 안 돼요."))
        list.add(MenteeData("국어", "이미지 첨부", "문학", "해석이 어려워요."))
        list.add(MenteeData("영어", "이미지 첨부", "2단원", "해석이 어려워요."))
        list.add(MenteeData("국어", "이미지 첨부", "문학", "해석이 어려워요."))
        list.add(MenteeData("코딩", "이미지 첨부", "반복문(for문)", "조건이 왜 위와 같이 나오는지 모르겠어요!"))
        list.add(MenteeData("수학", "이미지 첨부", "확률과 통계", "이 문제가 왜 독립시행인지 이해가 안 돼요."))
        list.add(MenteeData("국어", "이미지 첨부", "문학", "해석이 어려워요."))
        list.add(MenteeData("영어", "이미지 첨부", "2단원", "해석이 어려워요."))

        val adapter = MenteeDataRVAdapter(list)
        val grid = GridLayoutManager(this, 2)

        viewBinding.recyclerViewMentee.adapter = adapter
        viewBinding.recyclerViewMentee.layoutManager = grid
    }
}

