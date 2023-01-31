package com.example.blu_e.mainPage

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blu_e.R
import com.example.blu_e.data.FaqAdapter
import com.example.blu_e.databinding.ItemQuestionBinding
import com.example.blu_e.databinding.RecycleviewMentorCardBinding

class MentorDataRVAdapter(private val items: ArrayList<MentorData>) : RecyclerView.Adapter<MentorDataRVAdapter.ViewHolder>() {
    //각 항목에 필요한 기능 구현, ViewHolder 반환
    inner class ViewHolder(private val viewBinding: RecycleviewMentorCardBinding): RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(mentorData: MentorData) {
            viewBinding.menteeCardTitle.text = mentorData.title
            viewBinding.menteeCardDesiredSubject.text = mentorData.sub
            viewBinding.menteeCardDesiredPeriod.text = mentorData.date
            viewBinding.menteeCardMethod.text = mentorData.method
            viewBinding.menteeCardArea.text = mentorData.region
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MentorDataRVAdapter.ViewHolder {
        val viewBinding = RecycleviewMentorCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    //항목 뷰에 데이터 연결
    override fun onBindViewHolder(holder: MentorDataRVAdapter.ViewHolder, position: Int) {
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
