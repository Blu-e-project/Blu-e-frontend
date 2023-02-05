package com.example.blu_e.data.customercenter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blu_e.databinding.ItemQuestionBinding

class FaqAdapter(private val items: ArrayList<FaqData>): RecyclerView.Adapter<FaqAdapter.FaqDataViewHolder>() {
    inner class FaqDataViewHolder (private val viewBinding: ItemQuestionBinding): RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(questionItem: FaqData) {
            viewBinding.itemData.text = questionItem.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaqDataViewHolder {
        var viewBinding = ItemQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FaqDataViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: FaqDataViewHolder, position: Int) {
        val model = items!![position]
        holder.bind(model)
        holder.itemView.setOnClickListener {
            Log.d("selected", position.toString())
            itemClickListener.onClick(it, position)
        }
    }

    override fun getItemCount(): Int = items.size

    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }
    private lateinit var itemClickListener: ItemClickListener
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}
