package com.example.blu_e.mainPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.blu_e.databinding.ActivityHomeRecruitMentorBinding

class HomeRecruitMentorActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityHomeRecruitMentorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityHomeRecruitMentorBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val list: ArrayList<MentorData> = arrayListOf()

        list.apply {
            add(MentorData("매주 화요일에 멘토링 원해요", "국어", "23.01 ~ 23.03", "온라인", "서울 성북구"))
            add(MentorData("코딩이 너무 어려워요", "코딩", "23.01 ~ 23.03", "온라인", "부천 원미구"))
            add(MentorData("다정하고 친절하신 선생님...", "수학", "23.01 ~ 23.03", "온라인", "부천 오정구"))
            add(MentorData("비문학 쉽게 풀고 싶어요", "국어", "23.01 ~ 23.03", "온라인", "인천 부평구"))
            add(MentorData("매주 목요일에 멘토링 원해요", "영어", "23.01 ~ 23.03", "온라인", "서울 성북구"))
        }
            val mentorAdapter = MentorDataRVAdapter(list)
            val grid = GridLayoutManager(applicationContext, 2)

            viewBinding.recyclerViewMentor.adapter = mentorAdapter
            viewBinding.recyclerViewMentor.layoutManager = grid
    }
}