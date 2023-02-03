package com.example.blu_e

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blu_e.data.RecruitMenteeData
import com.example.blu_e.data.RecruitMenteeRVAdapter
import com.example.blu_e.data.RecruitMentorData
import com.example.blu_e.databinding.ActivityMyPostCommentBinding
import com.example.blu_e.mainPage.MentorDataRVAdapter

class MyPostCommentActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMyPostCommentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMyPostCommentBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val dataList: ArrayList<RecruitMenteeData> = arrayListOf()
        val dataRVAdapter = RecruitMenteeRVAdapter(dataList)

        dataList.apply {
            add(RecruitMenteeData("서울", "코딩", "2012-01", "2022-10", "멘토링", "duwk"))
            add(RecruitMenteeData("서울", "코딩", "2012-01", "2022-10", "멘토링", "duwk"))
            add(RecruitMenteeData("서울", "코딩", "2012-01", "2022-10", "멘토링", "duwk"))
        }
        dataRVAdapter.notifyItemInserted(2)
        viewBinding.rvMentee.adapter = dataRVAdapter
        viewBinding.rvMentee.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val recruitMentorList: ArrayList<RecruitMentorData> = arrayListOf()
        val mentorRVAdaptor = RecruitMentorRVApdapter(recruitMentorList)

        recruitMentorList.apply {
            add(RecruitMentorData("서울", "코딩", "2012-01", "2022-10", "멘토링", "duwk"))
            add(RecruitMentorData("경기도", "국어", "2012-01", "2022-10", "멘토링", "duwk"))
            add(RecruitMentorData("서울 성북구", "수학", "2012-01", "2022-10", "멘토링", "duwk"))
            add(RecruitMentorData("서울", "코딩", "2012-01", "2022-10", "멘토링", "duwk"))
            add(RecruitMentorData("경기도", "국어", "2012-01", "2022-10", "멘토링", "duwk"))
            add(RecruitMentorData("서울 성북구", "수학", "2012-01", "2022-10", "멘토링", "duwk"))
            add(RecruitMentorData("서울", "코딩", "2012-01", "2022-10", "멘토링", "duwk"))
            add(RecruitMentorData("경기도", "국어", "2012-01", "2022-10", "멘토링", "duwk"))
            add(RecruitMentorData("서울 성북구", "수학", "2012-01", "2022-10", "멘토링", "duwk"))
        }
        mentorRVAdaptor.notifyItemInserted(8)
        val grid = GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false)
        viewBinding.rvMentor.adapter = mentorRVAdaptor
        viewBinding.rvMentor.layoutManager = grid

    }
}