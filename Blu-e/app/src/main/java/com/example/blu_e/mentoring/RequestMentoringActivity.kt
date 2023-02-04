package com.example.blu_e.mentoring

import android.os.Bundle
import android.util.Log
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blu_e.R
import com.example.blu_e.data.*
import com.example.blu_e.databinding.ActivityRequestMentoringBinding
import java.sql.Timestamp

class RequestMentoringActivity : AppCompatActivity()  {
    private lateinit var viewBinding: ActivityRequestMentoringBinding
    private lateinit var adapter: RequestMentoringCommentAdapter
    private lateinit var commentExampleList: ArrayList<PickMemberComment>
//    private val api = RetroInterface.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityRequestMentoringBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        commentExampleList = ArrayList<PickMemberComment>()
        var commentExample: PickMemberComment
        for (i in 0..2) {
            commentExample = PickMemberComment(i)
            commentExampleList.add(i, commentExample)
        }
//        commentExampleList.get(0).userId = 100
        commentExampleList.get(0).contents = "수락해주세요!"
        commentExampleList.get(0).createdAt = Timestamp(System.currentTimeMillis())
//        commentExampleList.get(1).userId = 101
        commentExampleList.get(1).contents = "수락 부탁드려요!"
        commentExampleList.get(1).createdAt = Timestamp(System.currentTimeMillis() + 1)
//        commentExampleList.get(2).userId = 102
        commentExampleList.get(2).contents = "수락 부탁합니다!"
        commentExampleList.get(2).createdAt = Timestamp(System.currentTimeMillis() + 2)

        Log.d("checkList", commentExampleList.toString())
        adapter = RequestMentoringCommentAdapter(commentExampleList, this)
        viewBinding.recyclerViewComment.layoutManager = LinearLayoutManager(this)
        viewBinding.recyclerViewComment.adapter = adapter

        viewBinding.requestMemberPostDeleteIcon.setOnClickListener {
            var pop = PopupMenu(this, it)

            menuInflater.inflate(R.menu.popup_menu, pop.menu)

            pop.setOnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.deleteMenu ->
                        Toast.makeText(this,"삭제될겁니다.", Toast.LENGTH_SHORT).show()
                    R.id.updateMenu ->
                        Toast.makeText(this, "수정될겁니다.",Toast.LENGTH_SHORT).show()
                }
                false
            }
            pop.show()
        }

        viewBinding.sendButton?.setOnClickListener {
            var pickMemberComment = PickMemberComment(3)
            pickMemberComment.userId = 0
            pickMemberComment.pickWriterId = 0
            pickMemberComment.contents = ""
            pickMemberComment.createdAt = Timestamp(System.currentTimeMillis())
            /*api.commentCreate(0, 0, pickMemberComment).enqueue(object: Callback<PickMemberComment> {
                override fun onResponse(call: Call<PickMemberComment>, response: Response<PickMemberComment>) {
                }
                override fun onFailure(call: Call<PickMemberComment>, t: Throwable) {
                }
            })*/
        }
    }

    override fun onResume() {
        super.onResume()
        //0.0 글쓴이 님 로그인함 (댓글창 x)

            //1. 글을 불러와야 함 (글점3개 o), 댓글도 불러와야 함
            //2. 매칭중 여부,, 데이터 불러와서
            //3.0 매칭 중일 때
                //3.0.1 댓글들 보임.
                    //매칭 완료 UI x
                      adapter.updateCompletdTv(0)
                      adapter.updateAcceptBtnv(1)
            //3.1 매칭중 아닐 때 (매칭 완료)
                //3.1.1 댓글 매칭된 사람 하나만 보임. (그 사람 댓글 데이터의 매칭완료 데이터 불러와)
                    // 수락 UI x
        //0.1 댓쓴이 님 로그인 함 (댓글 창 x, 글점3개 x)
            //1. 내가 쓴 댓글
                //매칭 완료 UI x, 수락 UI x, 댓점3개 o
            //2. 다른 사람들이 쓴 댓글
                //매칭 완료 UI x, 수락 UI x, 댓점3개 x

        //0.2 일반 회원(잠재적 댓쓴이) 님 로그인 함 (댓글 창 o, 글점3개 x)
            //1. 다른 사람들이 쓴 댓글
                //매칭 완료 UI x, 수락 UI x, 댓점3개 x


        //+ 사용자 id, 글 id수정해야
        /* api.requestAllComments(0, 0).enqueue(object: Callback<ArrayList<PickMemberComment>> {
             override fun onResponse(call: Call<ArrayList<PickMemberComment>>, response: Response<ArrayList<PickMemberComment>>) {
                 var commentList = ArrayList<PickMemberComment>()
                 commentList.addAll(response.body() ?: return)
                 requestMentoringCommentadapter.commentListData = commentList
                 requestMentoringCommentadapter.notifyDataSetChanged()
             }

             override fun onFailure(call: Call<ArrayList<PickMemberComment>>, t: Throwable) {
                 //실패시
             }
         })*/

    }
}
