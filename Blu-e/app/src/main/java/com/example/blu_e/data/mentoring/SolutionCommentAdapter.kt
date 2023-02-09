package com.example.blu_e.data.mentoring

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.blu_e.R
import com.example.blu_e.databinding.ItemRequestMentoringCommentBinding
import com.example.blu_e.databinding.ItemSolutionCommentBinding

class SolutionCommentAdapter (private val commentListData: ArrayList<Solution>?, private val context: Context): RecyclerView.Adapter<SolutionCommentAdapter.SolutionCommentViewHolder>() {
    inner class SolutionCommentViewHolder (private val viewBinding: ItemSolutionCommentBinding): RecyclerView.ViewHolder(viewBinding.root) {
        val memberPicture = viewBinding.memberPicture
        val memberNickName = viewBinding.memberNickName
        val showWrittenDate = viewBinding.showWrittenDate
        val commentContent = viewBinding.commentContent

        fun bind(commentItem: Solution) {
            val url = ""
//          commentItem.userId?   .userImg
            Glide.with(context)
                .load(R.drawable.ic_baseline_person_24)
                .circleCrop()
//                .fallback(R.drawable.ic_baseline_person_24)
                .into(memberPicture)
//            memberNickName.text = User(commentItem.userId)   .nickname

            memberNickName.text = "블루님"
            showWrittenDate.text = commentItem.createdAt.toString()
            commentContent.text = commentItem.contents
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SolutionCommentViewHolder {
        var viewBinding = ItemSolutionCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SolutionCommentViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: SolutionCommentViewHolder, position: Int) {
        holder.bind(commentListData!![position])

    }

    override fun getItemCount(): Int {
        return commentListData!!.size
    }
}