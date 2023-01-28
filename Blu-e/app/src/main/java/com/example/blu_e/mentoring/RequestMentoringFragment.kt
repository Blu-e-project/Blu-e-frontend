package com.example.blu_e.mentoring

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.example.blu_e.MainActivity
import com.example.blu_e.data.PickMemberComment
import com.example.blu_e.data.RequestMentoringCommentAdapter
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.databinding.FragmentRequestMentoringBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestMentoringFragment : Fragment() {
    private lateinit var mContext: MainActivity
    private lateinit var viewBinding: FragmentRequestMentoringBinding
    private lateinit var requestMentoringCommentadapter: RequestMentoringCommentAdapter
    private val api = RetroInterface.create()

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

        viewBinding.recyclerViewComment.apply {
            val layoutManager = LinearLayoutManager(mContext)
            val decoration = DividerItemDecoration(mContext.applicationContext, VERTICAL)
            addItemDecoration(decoration)
            requestMentoringCommentadapter = RequestMentoringCommentAdapter()
            adapter = requestMentoringCommentadapter
        }
    }

    override fun onResume() {
        super.onResume()
        //+ 사용자 id, 글 id수정해야
        api.getComments(0, 0).enqueue(object: Callback<ArrayList<PickMemberComment>> {
            override fun onResponse(call: Call<ArrayList<PickMemberComment>>, response: Response<ArrayList<PickMemberComment>>) {
                var commentList = ArrayList<PickMemberComment>()
                commentList.addAll(response.body() ?: return)
                requestMentoringCommentadapter.commentListData = commentList
                requestMentoringCommentadapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<ArrayList<PickMemberComment>>, t: Throwable) {
                //실패시
            }
        })

    }
}