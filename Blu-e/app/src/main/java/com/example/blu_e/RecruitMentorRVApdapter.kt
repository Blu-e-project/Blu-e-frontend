package com.example.blu_e

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blu_e.data.RecruitMentorData
import com.example.blu_e.databinding.RecyclerviewMentorCardBinding

class RecruitMentorRVApdapter(private val dataList: ArrayList<RecruitMentorData> = arrayListOf()):
    RecyclerView.Adapter<RecruitMentorRVApdapter.DataViewHolder>(){
    inner class DataViewHolder(private val viewBinding:RecyclerviewMentorCardBinding):RecyclerView.ViewHolder(viewBinding.root){
     fun bind(data: RecruitMentorData) {
            viewBinding.menteeCardTitle.text = data.area
            viewBinding.menteeCardDesiredSubject.text = data.subject
            //var sdate = data.periodStart.format(DateTimeFormatter.ofPattern("yyyy-MM"))
            //var edate = data.periodEnd.format(DateTimeFormatter.ofPattern("yyyy-MM"))
            //viewBinding.menteeCardDesiredStartPeriod.text = sdate.toString()
            //viewBinding.menteeCardDesiredEndPeriod.text = edate.toString()
            viewBinding.menteeCardDesiredStartPeriod.text = data.periodStart
            viewBinding.menteeCardDesiredPeriod2.text = data.periodEnd
            viewBinding.menteeCardMethod.text = data.metoringMethod
            viewBinding.menteeCardAreaTitle.text= "[희망 성별]"
            viewBinding.menteeCardArea.text= data.mentorGender
     }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val viewBinding = RecyclerviewMentorCardBinding.inflate(LayoutInflater.from(parent.context), parent,false)
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