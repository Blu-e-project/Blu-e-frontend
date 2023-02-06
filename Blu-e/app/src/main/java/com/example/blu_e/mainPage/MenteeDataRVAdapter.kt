package com.example.blu_e.mainPage

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.blu_e.data.MenteeData
import com.example.blu_e.databinding.RecyclerviewMenteeCardBinding

class MenteeDataRVAdapter(private val dataList: ArrayList<MenteeData> = arrayListOf()):
    RecyclerView.Adapter<MenteeDataRVAdapter.DataViewHolder>() {
    inner class DataViewHolder(private val viewBinding: RecyclerviewMenteeCardBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        @RequiresApi(Build.VERSION_CODES.O)

        //viewHolder가 어떤걸 표시할 때 호출 시켜주는함수 data class를 인자값으로, 뷰 설정
        fun bind(data: MenteeData) {
            viewBinding.menteeCardTitle.text = data.title
            viewBinding.menteeCardDesiredSubject.text = data.subject
            viewBinding.menteeCardDesiredStartPeriod.text = data.periodStart
            viewBinding.menteeCardDesiredEndPeriod.text = data.periodEnd
            viewBinding.menteeCardMethod.text = data.mentoringMethod
            viewBinding.menteeCardArea.text = data.area
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val viewBinding = RecyclerviewMenteeCardBinding.inflate(LayoutInflater.from(parent.context), parent, false) //attachToParent 부모에 붙이겠니?? true하면 오류, layoutInflater를 바로 가져올 수 없어서 부모의 context정보를 이용
        //main에 viewbinding과 하는 짓 같음  layoutInflate를 바로 가져올 수 없어서 부모의 context정보 이용

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

