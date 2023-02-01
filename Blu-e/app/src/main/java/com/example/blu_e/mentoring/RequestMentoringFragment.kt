package com.example.blu_e.mentoring

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.example.blu_e.MainActivity
import com.example.blu_e.MyPageFragment
import com.example.blu_e.customercenter.AccusationFragment
import com.example.blu_e.customercenter.CenterFragment
import com.example.blu_e.customercenter.FaqDetailFragment
import com.example.blu_e.customercenter.QuestionFormFragment
import com.example.blu_e.data.*
import com.example.blu_e.databinding.FragmentRequestMentoringBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.sql.Time
import java.sql.Timestamp

class RequestMentoringFragment : Fragment() {
    private lateinit var mContext: MainActivity
    private lateinit var viewBinding: FragmentRequestMentoringBinding
    private lateinit var adapter: RequestMentoringCommentAdapter
    private lateinit var commentExampleList: ArrayList<PickMemberComment>
//    private val api = RetroInterface.create()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentRequestMentoringBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

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
        viewBinding.recyclerViewComment.layoutManager = LinearLayoutManager(mContext)
        viewBinding.recyclerViewComment.adapter = adapter

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