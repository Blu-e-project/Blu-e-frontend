package com.example.blu_e.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blu_e.databinding.ItemQuestionBinding
import com.example.blu_e.databinding.ItemRequestMentoringCommentBinding
import java.sql.Timestamp

class RequestMentoringCommentAdapter: RecyclerView.Adapter<RequestMentoringCommentAdapter.RequestMentoringCommentViewHolder>() {
    var commentListData = ArrayList<PickMemberComment>()

    inner class RequestMentoringCommentViewHolder (private val viewBinding: ItemRequestMentoringCommentBinding): RecyclerView.ViewHolder(viewBinding.root) {
        val memberPicture = viewBinding.memberPicture
        val memberNickName = viewBinding.memberNickName
        val showWrittenDate = viewBinding.showWrittenDate
        val commentContent = viewBinding.commentContent
        //acceptButton, denyButton
        fun bind(commentItem: PickMemberComment) {
            /*
            Glide 라이브러리 아직 추가 안 함. + User dto 생기면 수정
            val url = commentItem.userId?.userImg
            Glide.with(memberPicture)
                .load(url)
                .circleCrop()
                .into(memberPicture)*/
//            memberNickName.text = commentItem.userId.nickname
            showWrittenDate.text = commentItem.createdAt.toString()
            commentContent.text = commentItem.contents
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestMentoringCommentViewHolder {
        var viewBinding = ItemRequestMentoringCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RequestMentoringCommentViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: RequestMentoringCommentViewHolder, position: Int) {
        holder.bind(commentListData[position])
    }

    override fun getItemCount(): Int {
        return commentListData.size
    }
}