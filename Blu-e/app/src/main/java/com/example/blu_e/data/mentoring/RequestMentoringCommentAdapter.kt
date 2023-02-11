package com.example.blu_e.data.mentoring

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.blu_e.MainApplication
import com.example.blu_e.R
import com.example.blu_e.data.ResponseData
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.databinding.ItemRequestMentoringCommentBinding
import com.example.blu_e.mentoring.ProfileActivity
import com.example.blu_e.mentoring.RequestMentoringActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestMentoringCommentAdapter(private val commentListData: ArrayList<PickComment>?, private val context: Context , private val listener: (PickComment) -> Unit): RecyclerView.Adapter<RequestMentoringCommentAdapter.RequestMentoringCommentViewHolder>() {
    private val api = RetroInterface.create()
    private var acceptCheck = 1
    private var completedCheck = 0
    private var menuCheck = 1
    private var userId = MainApplication.prefs.getString("userId", "")
    private var role = MainApplication.prefs.getString("role", "")
    private var matchedPos = 0

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
        var commentId = 0

        fun bind(commentItem: PickComment) {
            val url = ""
            Glide.with(context)
                .load(commentItem.userImg)
                .circleCrop()
                .into(memberPicture)
//            memberNickName.text = User(commentItem.userId)   .nickname
            memberNickName.text = commentItem.nickname
            showWrittenDate.text = commentItem.createdAt.toString()
            commentContent.text = commentItem.contents
            commentId = commentItem.pickCommentId

            //댓쓴이와 유저 비교
            Log.d("commenId", commentItem.userId.toString())
            Log.d("userId", userId)
            if (commentItem.userId == userId.toInt()) {
                menuCheck = 1
                Log.d("점 3개 보여라 얍", "!")
            } else {
                menuCheck = 0
            }
            if(acceptCheck == 1) { accpetButton.visibility = View.VISIBLE } else { accpetButton.visibility = View.GONE }
            if(completedCheck == 1) { completedText.visibility = View.VISIBLE } else { completedText.visibility = View.GONE }
            if(menuCheck == 1) { changeCommentMenu.visibility = View.VISIBLE } else { changeCommentMenu.visibility = View.GONE }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestMentoringCommentViewHolder {
        var viewBinding = ItemRequestMentoringCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RequestMentoringCommentViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: RequestMentoringCommentViewHolder, position: Int, payloads: MutableList<Any>) {
        if(payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        }
        else {
            for (payload in payloads) {
                if (payload is String) {
                    if (payload.equals("onlyMatchedMemberWillBeSurvived")) {
                        val pos = position
                        Log.d("위치", pos.toString())
                        Log.d("matchedPos", matchedPos.toString())
                        if(matchedPos != pos) {
                            holder.accpetButton.visibility = View.GONE
                            holder.completedText.visibility = View.GONE
                            holder.changeCommentMenu.visibility = View.GONE
                        } else {
                            holder.accpetButton.visibility = View.GONE
                            holder.completedText.visibility = View.VISIBLE
                            holder.changeCommentMenu.visibility = View.GONE
                            Log.d("매칭된 사람이어야 하는데 ",  holder.memberNickName.toString())
                        }
                    }
                }
            }
        }
    }


    override fun onBindViewHolder(holder: RequestMentoringCommentViewHolder, position: Int) {
        holder.bind(commentListData!![position])
        if(acceptCheck == 1 && completedCheck == 0) {
            holder.accpetButton!!.setOnClickListener {
                holder.completedText.visibility = View.VISIBLE
                holder.accpetButton.visibility = View.GONE
                holder.changeCommentMenu.visibility = View.GONE
                acceptCheck = 0
                completedCheck = 1
                menuCheck = 0
                //매칭 안 된 사람들 댓글 수락, 매칭 UI 없애기
                matchedPos = holder.adapterPosition
                listener(commentListData[position])

                api.requestMatching(RequestMentoringActivity.pickId, holder.commentId).enqueue(object: Callback<ResponseData> {
                    override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                        //성공시
                        val body = response.body()?: return
                        Toast.makeText(context, body.message, Toast.LENGTH_SHORT).show()
                        Log.d("멘토 멘티 매칭되었습니다.", body.message)
                    }

                    override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                        //실패시
                        Log.d("멘토 멘티 매칭되었습니다.", "실패")
                    }
                })
            }
        }
        if(menuCheck == 1) {
            holder.changeCommentMenu.setOnClickListener {
                var pop = PopupMenu(context, it)
                pop.menuInflater.inflate(R.menu.popup_menu, pop.menu)

                pop.setOnMenuItemClickListener {
                    if(it.itemId == R.id.deleteMenu) {
                        Log.d("댓글메뉴확인", "삭제될겁니다.")
                        if(role.toInt() == 1) {
                            api.commentDeleteInMentorPost(RequestMentoringActivity.pickId, holder.commentId).enqueue(object: Callback<ResponseData> {
                                override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                                    //성공시
                                    val body = response.body()?: return
                                    Log.d("멘토 구인글의 댓글 삭제", body.message)
                                }

                                override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                                    //실패시
                                    Log.d("멘토 구인글의 댓글 삭제", "실패")
                                }
                            })
                        }
                        else if(role.toInt() == 2) {
                            api.commentDeleteInMenteePost(RequestMentoringActivity.pickId, holder.commentId).enqueue(object: Callback<ResponseData> {
                                override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                                    //성공시
                                    val body = response.body()?: return
                                    Log.d("멘티 구인글의 댓글 삭제", body.message)
                                }

                                override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                                    //실패시
                                    Log.d("멘티 구인글의 댓글 삭제", "실패")
                                }
                            })
                        }
                    }
                    else if(it.itemId == R.id.updateMenu) {
                            Log.d("댓글메뉴확인", "수정될겁니다.")
                            //수정 폼..필요?
                            //api.commentUpdate(0,)
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
        return commentListData!!.size
    }
}