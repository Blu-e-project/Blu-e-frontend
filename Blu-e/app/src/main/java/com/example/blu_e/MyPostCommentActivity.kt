package com.example.blu_e

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.blu_e.data.MentoringHistoryData
import com.example.blu_e.data.MentoringHistoryDataRVAdapter
import com.example.blu_e.data.RecruitingMenteeData
import com.example.blu_e.data.RecruitingMenteeRVAdapter
import com.example.blu_e.databinding.ActivityMyPostCommentBinding

class MyPostCommentActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMyPostCommentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMyPostCommentBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val dataList: ArrayList<RecruitingMenteeData> = arrayListOf()
        val dataRVAdapter = RecruitingMenteeRVAdapter(dataList)

        dataList.apply {
            add(RecruitingMenteeData("서울", "코딩","2012-01", "2022-10", "멘토링","duwk"))
            add(RecruitingMenteeData("서울", "코딩","2012-01", "2022-10", "멘토링","duwk"))
            add(RecruitingMenteeData("서울", "코딩","2012-01", "2022-10", "멘토링","duwk"))
        }
        dataRVAdapter.notifyItemInserted(2)
        viewBinding.rvMentee.adapter = dataRVAdapter
        viewBinding.rvMentee.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    }
}