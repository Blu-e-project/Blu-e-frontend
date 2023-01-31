package com.example.blu_e.mainPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blu_e.R
import com.example.blu_e.databinding.ActivityHomeMentorBinding
import com.example.blu_e.databinding.ActivityHomeRecruitMentorBinding
import com.example.blu_e.databinding.ActivityMainBinding
import com.example.blu_e.databinding.FragmentCenterBinding
import com.example.blu_e.databinding.RecycleviewMentorCardBinding

class HomeRecruitMentorActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityHomeRecruitMentorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityHomeRecruitMentorBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val list = ArrayList<MentorData>()
        list.add(MentorData("매주 화요일에 멘토링 원해요", "국어", "23.01 ~ 23.03", "온라인", "성북구"))
        list.add(MentorData("매주 수요일에 멘토링 원해요", "국어", "23.01 ~ 23.03", "온라인", "성북구"))
        list.add(MentorData("매주 목요일에 멘토링 원해요", "국어", "23.01 ~ 23.03", "온라인", "성북구"))

        val adapter = MentorDataRVAdapter(list)
        viewBinding.lstUser.adapter = adapter
    }
}