package com.example.blu_e.data

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blu_e.databinding.ItemSettingShapeBinding

class ListInMyPageAdapter(private val listData: ArrayList<ListInMyPageData>) : RecyclerView.Adapter<ListInMyPageAdapter.listInMyPageAdapterViewHolder>() {

    inner class listInMyPageAdapterViewHolder (private val viewBinding: ItemSettingShapeBinding): RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(listInMyPageData: ListInMyPageData) {
            viewBinding.settingTitle.text = listInMyPageData.settingTitle
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListInMyPageAdapter.listInMyPageAdapterViewHolder {
        var viewBinding = ItemSettingShapeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return listInMyPageAdapterViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ListInMyPageAdapter.listInMyPageAdapterViewHolder, position: Int) {
        holder.bind(listData!![position])
        holder.itemView.setOnClickListener {
            Log.d("화면 이동", position.toString())
            itemClickListener.onClick(it, position)
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }

    private lateinit var itemClickListener: ItemClickListener
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}