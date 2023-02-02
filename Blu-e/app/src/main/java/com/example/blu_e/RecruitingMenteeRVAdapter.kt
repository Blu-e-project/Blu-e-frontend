package com.example.blu_e.data

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.blu_e.databinding.ActivityMyPostCommentBinding
import com.example.blu_e.databinding.RecyclerviewMentorCardBinding
import java.time.format.DateTimeFormatter

class RecruitingMenteeRVAdapter(private val dataList: ArrayList<RecruitingMenteeData> = arrayListOf()):
    RecyclerView.Adapter<RecruitingMenteeRVAdapter.DataViewHolder>() {
    inner class DataViewHolder(private val viewBinding:RecyclerviewMentorCardBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(data:RecruitingMenteeData) { //viewHolder가 어떤걸 표시할 때 호출 시켜주는함수 data class를 인자값으로, 뷰 설정
            viewBinding.menteeCardTitle.text = data.region
            viewBinding.menteeCardDesiredSubject.text = data.subject
            //var sdate = data.periodStart.format(DateTimeFormatter.ofPattern("yyyy-MM"))
            //var edate = data.periodEnd.format(DateTimeFormatter.ofPattern("yyyy-MM"))
            //viewBinding.menteeCardDesiredStartPeriod.text = sdate.toString()
            //viewBinding.menteeCardDesiredEndPeriod.text = edate.toString()
            viewBinding.menteeCardDesiredStartPeriod.text = data.periodStart
            viewBinding.menteeCardDesiredPeriod2.text = data.periodEnd
            viewBinding.menteeCardMethod.text = data.metoringMethod
            viewBinding.menteeCardAreaTitle.text= "[희망 성별]"
            viewBinding.menteeCardArea.text= data.menteeGender
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {

        val viewBinding = RecyclerviewMentorCardBinding.inflate(LayoutInflater.from(parent.context), parent, false) //attachToParent 부모에 붙이겠니?? true하면 오류, layoutInflater를 바로 가져올 수 없어서 부모의 context정보를 이용
        //main에 viewbinding과 하는짓 같음  layoutInflate를 바로 가져올 수 없어서 부모의 context정보 이용
        return DataViewHolder(viewBinding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

}