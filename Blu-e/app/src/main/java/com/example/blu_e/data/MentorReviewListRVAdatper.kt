package com.example.blu_e.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blu_e.databinding.ItemMentorMyReviewBinding
import com.example.blu_e.databinding.MentoringHistoryItemBinding

class MentorReviewListRVAdatper(private  val dataList: ArrayList<MentorReviewListData> = arrayListOf()): RecyclerView.Adapter<MentorReviewListRVAdatper.DataViewHolder>() {
    inner class DataViewHolder(private  val viewBinding: ItemMentorMyReviewBinding): RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(data: MentorReviewListData){
<<<<<<< HEAD
//            viewBinding.nicknameMenteeReviewList.text = data.menteeId
=======
            //viewBinding.nicknameMenteeReviewList.text = data.menteeId
>>>>>>> 780a561f256e4c82eb889bea4174feed4d8fb1e7
            viewBinding.menteeReviewText.text = data.contents
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val viewBinding = ItemMentorMyReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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