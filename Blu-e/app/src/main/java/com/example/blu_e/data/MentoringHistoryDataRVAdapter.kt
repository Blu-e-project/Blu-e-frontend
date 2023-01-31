package com.example.blu_e.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blu_e.databinding.MentoringHistoryItemBinding

class MentoringHistoryDataRVAdapter(private  val dataList: ArrayList<MentoringHistoryData> = arrayListOf()): RecyclerView.Adapter<MentoringHistoryDataRVAdapter.DataViewHolder>() {
    inner class DataViewHolder(private  val viewBinding:MentoringHistoryItemBinding): RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(data: MentoringHistoryData){
            viewBinding.nickname.text = data.mentee
            viewBinding.matchingBtn.text = data.matching
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val viewBinding = MentoringHistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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