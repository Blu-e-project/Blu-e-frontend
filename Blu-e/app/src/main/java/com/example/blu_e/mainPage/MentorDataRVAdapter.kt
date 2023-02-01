package com.example.blu_e.mainPage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blu_e.data.MentorData
import com.example.blu_e.databinding.RecyclerviewMentorCardBinding

class MentorDataRVAdapter(private val items: ArrayList<MentorData>) : RecyclerView.Adapter<MentorDataRVAdapter.ViewHolder>() {
    //각 항목에 필요한 기능 구현, ViewHolder 반환
    inner class ViewHolder(private val viewBinding: RecyclerviewMentorCardBinding): RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(data: MentorData) {
            viewBinding.menteeCardTitle.text = data.title
            viewBinding.menteeCardDesiredSubject.text = data.sub
            viewBinding.menteeCardDesiredPeriod.text = data.date
            viewBinding.menteeCardMethod.text = data.method
            viewBinding.menteeCardArea.text = data.region
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = RecyclerviewMentorCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    //항목 뷰에 데이터 연결
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = items[position]
        holder.bind(model)
    }

    //아이템 개수
    override fun getItemCount(): Int  = items.size
}
