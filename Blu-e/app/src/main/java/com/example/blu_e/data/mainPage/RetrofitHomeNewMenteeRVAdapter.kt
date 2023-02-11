package com.example.blu_e.data.mainPage

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blu_e.databinding.RecyclerviewNewMenteeCardBinding

class RetrofitHomeNewMenteeRVAdapter(private val items: ArrayList<FindFiveMenteeResponse.FindFiveMenteeItems> = arrayListOf()) :
    RecyclerView.Adapter<RetrofitHomeNewMenteeRVAdapter.ViewHolder>() {

    //각각의 Item을 클릭했을 때 이벤트 정의하기
    interface MyItemClickListener {
        fun onItemClick(item: FindFiveMenteeResponse.FindFiveMenteeItems)
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }


    //각 항목에 필요한 기능 구현, ViewHolder 반환
    inner class ViewHolder(private val viewBinding: RecyclerviewNewMenteeCardBinding):
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(data: FindFiveMenteeResponse.FindFiveMenteeItems) {
            viewBinding.pickMenteeId.text = data.nickname
        }
    }

    //뷰홀더 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = RecyclerviewNewMenteeCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(viewBinding)
    }

    //항목 뷰에 데이터 연결
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // ViewHolder와 리스트의 아이템 중 하나를 서로 연결
        val model = items!![position]
        holder.bind(model)

        holder.itemView.setOnClickListener {
            Log.d("selected", position.toString())
            mItemClickListener.onItemClick(items[position])
        }
    }

    //아이템 개수
    override fun getItemCount(): Int {
        Log.e("아이템 사이즈", "${items.size}")
        return items.size
    }

    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }

    private lateinit var itemClickListener: ItemClickListener
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}
