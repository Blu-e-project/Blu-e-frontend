package com.example.blu_e

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blu_e.data.MentorHistoryData
import com.example.blu_e.data.MentorHistoryDataRVAdapter
import com.example.blu_e.databinding.ActivityMentorHistoryBinding

class MentorHistoryActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMentorHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMentorHistoryBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val dataList: ArrayList<MentorHistoryData> = arrayListOf()
        val dataRVAdapter = MentorHistoryDataRVAdapter(dataList)

        dataList.apply {
            add(MentorHistoryData("블루이", "매칭 대기 중"))
            add(MentorHistoryData("hello", "멘토링 진행 중"))
            add(MentorHistoryData("진", "리뷰"))
            add(MentorHistoryData("유진", "멘토링 완료"))
        }
        dataRVAdapter.notifyItemInserted(2)
        viewBinding.rvData.adapter = dataRVAdapter
        viewBinding.rvData.layoutManager = LinearLayoutManager(this)


    }
}