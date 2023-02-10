package com.example.blu_e.data.mypage

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blu_e.databinding.ItemAboutMeReviewBinding

class MentorAboutMeReviewRVAdapter(private  val dataList: ArrayList<MentorReviewListData> = arrayListOf()): RecyclerView.Adapter<MentorAboutMeReviewRVAdapter.DataViewHolder>() {
    inner class DataViewHolder(private  val viewBinding: ItemAboutMeReviewBinding): RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(data: MentorReviewListData){
            viewBinding.nicknameMenteeReviewList.text = data.nickname
            viewBinding.menteeReviewText.text = data.contents
            Log.d("나에 대한 리뷰 어댑터에 집어넣기", "성공")
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MentorAboutMeReviewRVAdapter.DataViewHolder {
        val viewBinding = ItemAboutMeReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: MentorAboutMeReviewRVAdapter.DataViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    override fun getItemViewType(position: Int): Int {
        return position
    }
}