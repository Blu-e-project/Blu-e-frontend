package com.example.blu_e.data.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blu_e.MainApplication
import com.example.blu_e.databinding.MentoringHistoryItemBinding

class MentorHistoryDataRVAdapter(private  val dataList: ArrayList<MentorHistoryData> = arrayListOf()): RecyclerView.Adapter<MentorHistoryDataRVAdapter.DataViewHolder>() {
    inner class DataViewHolder(private  val viewBinding:MentoringHistoryItemBinding): RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(data: MentorHistoryData){
            viewBinding.nickname.text = data.mentee
            viewBinding.matchingBtn.text = data.matching
            var role = MainApplication.prefs.getString("role", "")
            var isMentor: Int = role.toInt() //멘토: 1, 멘티: 2
            if(role.toInt() == 2) { //멘티 로그인 시
                viewBinding.menteeTv.text="멘토"
            }
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