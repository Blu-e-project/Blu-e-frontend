package com.example.blu_e.data

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blu_e.databinding.ItemQuestionBinding

class FaqAdapter(private val items: ArrayList<Question>): RecyclerView.Adapter<FaqAdapter.QuestionViewHolder>() {
    //한 화면에서 전부 나오게 하면 안되고.. 더보기 필요할 수도
    inner class QuestionViewHolder (private val viewBinding: ItemQuestionBinding): RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(questionItem: Question) {
            viewBinding.itemData.text = questionItem.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        var viewBinding = ItemQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuestionViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener() {
            Log.d("selected", position.toString())
            itemClickListener.onClick(it, position)
        }
    }

    override fun getItemCount(): Int = items.size

    //fragment <-> fragment
    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }
    private lateinit var itemClickListener: ItemClickListener
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}