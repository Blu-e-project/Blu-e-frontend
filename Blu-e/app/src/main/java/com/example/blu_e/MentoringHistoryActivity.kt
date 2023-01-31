package com.example.blu_e

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blu_e.data.MentoringHistoryData
import com.example.blu_e.data.MentoringHistoryDataRVAdapter
import com.example.blu_e.databinding.ActivityMentoringHistoryBinding

class MentoringHistoryActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMentoringHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMentoringHistoryBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val dataList: ArrayList<MentoringHistoryData> = arrayListOf()
        val dataRVAdapter = MentoringHistoryDataRVAdapter(dataList)

        dataList.apply {
            add(MentoringHistoryData("블루이", "매칭 대기중"))
            add(MentoringHistoryData("hello", "멘토링 진행중"))
            add(MentoringHistoryData("시니", "멘토링 진행중"))
        }
        dataRVAdapter.notifyItemInserted(2)
        viewBinding.rvData.adapter = dataRVAdapter
        viewBinding.rvData.layoutManager = LinearLayoutManager(this)


    }
}