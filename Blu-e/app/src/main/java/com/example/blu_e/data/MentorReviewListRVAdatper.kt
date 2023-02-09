package com.example.blu_e.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blu_e.databinding.ItemMentorReviewListBinding
import com.example.blu_e.databinding.MentoringHistoryItemBinding

class MentorReviewListRVAdatper(private  val dataList: ArrayList<MentorReviewListData> = arrayListOf()): RecyclerView.Adapter<MentorReviewListRVAdatper.DataViewHolder>() {
    inner class DataViewHolder(private  val viewBinding: ItemMentorReviewListBinding): RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(data: MentorReviewListData){
            viewBinding.nicknameMenteeReviewList.text = data.menteeId
            viewBinding.menteeReviewText.text = data.contents
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val viewBinding = ItemMentorReviewListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    override fun getItemViewType(position: Int): Int {
        return position
    }
}