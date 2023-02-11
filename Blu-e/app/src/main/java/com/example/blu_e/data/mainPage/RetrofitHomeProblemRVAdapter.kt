package com.example.blu_e.data.mainPage

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blu_e.databinding.RecyclerviewMenteeCardBinding
import com.example.blu_e.databinding.RecyclerviewQuestionCardBinding

class RetrofitHomeProblemRVAdapter(private val items: ArrayList<FindFiveProblemItems> = arrayListOf()) :
    RecyclerView.Adapter<RetrofitHomeProblemRVAdapter.ViewHolder>() {
    //각 항목에 필요한 기능 구현, ViewHolder 반환
    inner class ViewHolder(private val viewBinding: RecyclerviewQuestionCardBinding):
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(data: FindFiveProblemItems) {
            viewBinding.questionCardSubject.text = data.subject
            viewBinding.questionCardProblem.text = data.problem
            viewBinding.questionCardUnit.text = data.unit
            viewBinding.questionCardContents.text = data.contents
        }
    }

    //뷰홀더 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetrofitHomeProblemRVAdapter.ViewHolder {
        val viewBinding = RecyclerviewQuestionCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(viewBinding)
    }

    //항목 뷰에 데이터 연결
    override fun onBindViewHolder(holder: RetrofitHomeProblemRVAdapter.ViewHolder, position: Int) {
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
