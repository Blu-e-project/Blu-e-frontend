package com.example.blu_e.data.mainPage

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blu_e.databinding.RecyclerviewMenteeCardBinding
import com.example.blu_e.databinding.RecyclerviewMentorCardBinding

class RetrofitRecruitMentorRVAdapter(private val items: ArrayList<FindRecruitMenteeResponse.FindRecruitMenteeItem> = arrayListOf()) :
    RecyclerView.Adapter<RetrofitRecruitMentorRVAdapter.ViewHolder>() {
    //각 항목에 필요한 기능 구현, ViewHolder 반환
    inner class ViewHolder(private val viewBinding: RecyclerviewMentorCardBinding):
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(data: FindRecruitMenteeResponse.FindRecruitMenteeItem) {
            viewBinding.mentorCardTitle.text = data.title
            viewBinding.mentorCardDesiredSubject.text = data.subject
            viewBinding.mentorCardDesiredPeriod.text = data.period
            viewBinding.mentorCardMethod.text = data.mentoringMethod
            viewBinding.mentorCardGender.text = data.wishGender
        }
    }

    //뷰홀더 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetrofitRecruitMentorRVAdapter.ViewHolder {
        val viewBinding = RecyclerviewMentorCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(viewBinding)
    }

    //항목 뷰에 데이터 연결
    override fun onBindViewHolder(holder: RetrofitRecruitMentorRVAdapter.ViewHolder, position: Int) {
        val model = items!![position]
        holder.bind(model)
        holder.itemView.setOnClickListener {
            Log.d("selected", position.toString())
            itemClickListener.onClick(it, position)
        }
    }

    //아이템 개수
    override fun getItemCount(): Int = items.size

    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }

    private lateinit var itemClickListener: ItemClickListener
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}
