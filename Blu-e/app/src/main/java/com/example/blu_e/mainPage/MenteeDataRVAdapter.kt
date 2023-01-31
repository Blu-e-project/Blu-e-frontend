package com.example.blu_e.mainPage

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blu_e.databinding.RecyclerviewQuestionCardBinding

class MenteeDataRVAdapter(private val items: ArrayList<MenteeData>) : RecyclerView.Adapter<MenteeDataRVAdapter.ViewHolder>() {
    //각 항목에 필요한 기능 구현, ViewHolder 반환
    inner class ViewHolder(private val viewBinding: RecyclerviewQuestionCardBinding): RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(MenteeData: MenteeData) {
            viewBinding.questionCardSubject.text = MenteeData.sub
            viewBinding.questionDesiredProblem.text = MenteeData.problem
            viewBinding.questionCardDesiredrange.text = MenteeData.range
            viewBinding.questionCardDesiredQuestion.text = MenteeData.question
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenteeDataRVAdapter.ViewHolder {
        val viewBinding = RecyclerviewQuestionCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    //항목 뷰에 데이터 연결
    override fun onBindViewHolder(holder: MenteeDataRVAdapter.ViewHolder, position: Int) {
        val model = items[position]
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
