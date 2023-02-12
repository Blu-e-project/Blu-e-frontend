package com.example.blu_e.data.mypage

import android.app.AlertDialog
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blu_e.MainActivity
import com.example.blu_e.MainApplication
import com.example.blu_e.databinding.ItemMentorMyReviewBinding

class MentorReviewListRVAdatper(private  val dataList: ArrayList<MentorReviewListData> = arrayListOf()): RecyclerView.Adapter<MentorReviewListRVAdatper.DataViewHolder>() {
    inner class DataViewHolder(private  val viewBinding: ItemMentorMyReviewBinding): RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(data: MentorReviewListData){
            viewBinding.nicknameMenteeReviewList.text = data.nickname
            viewBinding.menteeReviewText.text = data.contents
            var role = MainApplication.prefs.getString("role", "")
            var isMentor: Int = role.toInt() //멘토: 1, 멘티: 2
            if(role.toInt() == 2) { //멘티 로그인 시
                viewBinding.menteeTv.text="멘토"
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val viewBinding = ItemMentorMyReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(dataList[position])
        holder.itemView.setOnClickListener {
            Log.d("selected", position.toString())
            itemClickListener.onClick(it, position)
        }
    }

    override fun getItemCount(): Int = dataList.size

    override fun getItemViewType(position: Int): Int {
        return position
    }
    //item 클릭시 리뷰 수정/삭제
    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }
    private lateinit var itemClickListener: ItemClickListener
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

}