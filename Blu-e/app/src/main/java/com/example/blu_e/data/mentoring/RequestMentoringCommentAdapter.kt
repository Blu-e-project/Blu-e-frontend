package com.example.blu_e.data.mentoring

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.blu_e.ProfileActivity
import com.example.blu_e.R
import com.example.blu_e.databinding.ItemRequestMentoringCommentBinding


class RequestMentoringCommentAdapter(private val commentListData: ArrayList<PickMemberComment>, private val context: Context): RecyclerView.Adapter<RequestMentoringCommentAdapter.RequestMentoringCommentViewHolder>() {
    private var acceptCheck = 1
    private var completedCheck = 0
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

                //****여기가 관건.. 매칭 안 된 사람들 댓글 수락, 매칭 UI 없애기 -> listener(commentListData[position]) or 새로고침****
                //매칭 됐다고 서버에 알리기 (댓글 다 삭제해 줄 예정)
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
        //댓쓴이 프로필 클릭시
        holder.memberPicture.setOnClickListener {
            //if 멘티 라면
            //else 멘토 라면
            var intent = Intent(context, ProfileActivity::class.java)
            //userId 보내기
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return commentListData.size
    }
}