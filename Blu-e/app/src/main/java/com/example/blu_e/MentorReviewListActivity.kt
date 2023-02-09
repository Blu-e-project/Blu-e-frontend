package com.example.blu_e

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blu_e.data.MentorReviewListData
import com.example.blu_e.data.MentorReviewListRVAdatper
import com.example.blu_e.databinding.ActivityMentorMyReviewBinding

class MentorReviewListActivity:AppCompatActivity() {
    private lateinit var mContext: MainActivity
    lateinit var viewBinding: ActivityMentorMyReviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMentorMyReviewBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val dataList: ArrayList<MentorReviewListData> = arrayListOf()
        val dataRVAdapter = MentorReviewListRVAdatper(dataList)

        dataList.apply {
            add(MentorReviewListData("임서희", "멘토님께서 친절하게 설명해주셔서 좋았어요! 기회가 된다면 또 뵙고 싶어요."))
            add(MentorReviewListData("고유진", "감사했습니다"))
            add(MentorReviewListData("진", "이해하기 쉽게 설명해주셨습니다"))
        }
        dataRVAdapter.notifyItemInserted(2)
        viewBinding.rvDataMenteeList.adapter = dataRVAdapter
        viewBinding.rvDataMenteeList.layoutManager = LinearLayoutManager(this)

        viewBinding.backToMypage.setOnClickListener{
            finish()
        }

    }
}