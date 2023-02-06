package com.example.blu_e.mainPage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blu_e.data.mainPage.NewMenteeData
import com.example.blu_e.databinding.RecyclerviewNewMenteeCardBinding

class RetrofitNewMenteeDataRVAdapter(private val items: ArrayList<NewMenteeData> = arrayListOf()) :
    RecyclerView.Adapter<RetrofitNewMenteeDataRVAdapter.ViewHolder>() {
    //각 항목에 필요한 기능 구현, ViewHolder 반환
    inner class ViewHolder(private val viewBinding: RecyclerviewNewMenteeCardBinding):
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(data: NewMenteeData) {
            viewBinding.pickMenteeId.text = data.pickMenteeId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetrofitNewMenteeDataRVAdapter.ViewHolder {
        val viewBinding = RecyclerviewNewMenteeCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(viewBinding)
    }

    //항목 뷰에 데이터 연결
    override fun onBindViewHolder(holder: RetrofitNewMenteeDataRVAdapter.ViewHolder, position: Int) {
        val model = items[position]
        holder.bind(model)
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
