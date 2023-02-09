package com.example.blu_e.data.mainPage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blu_e.databinding.RecyclerviewNewMenteeCardBinding
import com.example.blu_e.mainPage.NewMenteeDataRVAdapter

class RetrofitHomeNewMenteeRVAdapter(private val items: ArrayList<FindFiveMenteeResponse.FindFiveMenteeItems> = arrayListOf()) :
    RecyclerView.Adapter<RetrofitHomeNewMenteeRVAdapter.ViewHolder>() {
    //각 항목에 필요한 기능 구현, ViewHolder 반환
    inner class ViewHolder(private val viewBinding: RecyclerviewNewMenteeCardBinding):
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(data: FindFiveMenteeResponse.FindFiveMenteeItems) {
            viewBinding.pickMenteeId.text = data.nickname
        }
    }

    //뷰홀더 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetrofitHomeNewMenteeRVAdapter.ViewHolder {
        val viewBinding = RecyclerviewNewMenteeCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(viewBinding)
    }

    //항목 뷰에 데이터 연결
    override fun onBindViewHolder(holder: RetrofitHomeNewMenteeRVAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
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