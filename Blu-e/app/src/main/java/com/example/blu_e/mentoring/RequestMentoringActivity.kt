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
        adapter = RequestMentoringCommentAdapter(commentExampleList)
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
