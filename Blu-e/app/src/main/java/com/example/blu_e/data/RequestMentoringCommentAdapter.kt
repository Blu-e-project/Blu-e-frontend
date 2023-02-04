package com.example.blu_e.data

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.blu_e.R
import com.example.blu_e.databinding.ItemRequestMentoringCommentBinding
import com.example.blu_e.mentoring.RequestMentoringActivity


class RequestMentoringCommentAdapter(private val commentListData: ArrayList<PickMemberComment>, private val context: Context): RecyclerView.Adapter<RequestMentoringCommentAdapter.RequestMentoringCommentViewHolder>() {
    private var acceptCheck = 1
    private var completedCheck = 1
    private var menuCheck = 1

    fun updateAcceptBtnv(n: Int) {
        acceptCheck = n
    }
    fun updateCompletdTv(n: Int) {
        completedCheck = n
    }
    fun updateCommentMenuv(n: Int) {
        menuCheck = n
    }

    inner class RequestMentoringCommentViewHolder (private val viewBinding: ItemRequestMentoringCommentBinding): RecyclerView.ViewHolder(viewBinding.root) {
        val memberPicture = viewBinding.memberPicture
        val memberNickName = viewBinding.memberNickName
        val showWrittenDate = viewBinding.showWrittenDate
        val commentContent = viewBinding.commentContent
        //버튼
        var accpetButton = viewBinding.acceptButton
        var completedText = viewBinding.completedText
        var changeCommentMenu = viewBinding.requestMemberCommentDeleteIcon

        fun bind(commentItem: PickMemberComment) {
            /*
            Glide 라이브러리 아직 추가 안 함. + User dto 생기면 수정
            val url = commentItem.userId?.userImg
            Glide.with(memberPicture)
                .load(url)
                .circleCrop()
                .into(memberPicture)*/
//            memberNickName.text = User(commentItem.userId).nickname

            memberNickName.text = "블루님"
            showWrittenDate.text = commentItem.createdAt.toString()
            commentContent.text = commentItem.contents

            if(acceptCheck == 1) { accpetButton.visibility = View.VISIBLE } else { accpetButton.visibility = View.GONE }
            if(completedCheck == 1) { completedText.visibility = View.VISIBLE } else { completedText.visibility = View.GONE }
            if(menuCheck == 1) { changeCommentMenu.visibility = View.VISIBLE } else { changeCommentMenu.visibility = View.GONE }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestMentoringCommentViewHolder {
        var viewBinding = ItemRequestMentoringCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RequestMentoringCommentViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: RequestMentoringCommentViewHolder, position: Int) {
        holder.bind(commentListData[position])
        if(acceptCheck == 1 && completedCheck == 0) {
            holder.accpetButton!!.setOnClickListener {
                holder.completedText.visibility = View.VISIBLE
                holder.accpetButton.visibility = View.GONE
                holder.changeCommentMenu.visibility = View.GONE
                acceptCheck = 0
                completedCheck = 1
                menuCheck = 0
                //버튼 상태(수락 여부) 서버에 알리기
            }
        }
        if(menuCheck == 1) {
            holder.changeCommentMenu.setOnClickListener {
                var pop = PopupMenu(context, it)
                pop.menuInflater.inflate(R.menu.popup_menu, pop.menu)

                pop.setOnMenuItemClickListener { item ->
                    when(item.itemId) {
                        R.id.deleteMenu -> Log.d("댓글메뉴확인", "삭제될겁니다.")
                        R.id.updateMenu -> Log.d("댓글메뉴확인", "수정될겁니다.")
                    }
                    false
                }
                pop.show()
            }
        }
    }

    override fun getItemCount(): Int {
        return commentListData.size
    }
}