package com.example.blu_e.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blu_e.MainApplication
import com.example.blu_e.databinding.MentoringAlarmItemBinding

class MentorAlarmDataRVAdapter(private val dataList: ArrayList<MentorAlarmData> = arrayListOf()) : RecyclerView.Adapter<MentorAlarmDataRVAdapter.DataViewHolder>(){
    inner class DataViewHolder(private val viewBinding:MentoringAlarmItemBinding): RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(data: MentorAlarmData){
            viewBinding.nicknameMentee.text = data.mentee
            viewBinding.mentoringStatus.text = data.mathcing
            viewBinding.mentoringStatus2.text=data.mathcing2
            /*var role = MainApplication.prefs.getString("role", "")
            var isMentor: Int = role.toInt() //멘토: 1, 멘티: 2
            if(role.toInt() == 2) { //멘티 로그인 시
                viewBinding.mentoringStatus.text="멘토 신청"
            }*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val viewBinding = MentoringAlarmItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
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