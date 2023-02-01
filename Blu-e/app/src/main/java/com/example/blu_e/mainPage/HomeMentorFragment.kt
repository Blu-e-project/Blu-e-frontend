package com.example.blu_e.mainPage

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blu_e.MainActivity
import com.example.blu_e.customercenter.QuestionDetailFragment
import com.example.blu_e.customercenter.QuestionDetailFragment.Companion.newInstance
import com.example.blu_e.data.MenteeData
import com.example.blu_e.data.QuestionData
import com.example.blu_e.databinding.FragmentHomeMentorBinding

class HomeMentorFragment : Fragment() {
    private lateinit var mContext: MainActivity
    private lateinit var viewBinding: FragmentHomeMentorBinding
    private lateinit var questionList: ArrayList<QuestionData>
    private lateinit var menteeList: ArrayList<MenteeData>
    private lateinit var questionAdapter: QuestionDataRVAdapter
    private lateinit var menteeAdapter: MenteeDataRVAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentHomeMentorBinding.inflate(inflater, container, false)
        Log.d("왜않되", "1")
/*

        //새로운 멘티가 있어요!
        menteeAdapter = MenteeDataRVAdapter(menteeList)
        viewBinding.recyclerViewNewMentee.adapter = menteeAdapter
        viewBinding.recyclerViewNewMentee.layoutManager =
            LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)

        //궁금한 문제가 있어요!
        questionAdapter = QuestionDataRVAdapter(questionList)
        viewBinding.recyclerViewQuestion.adapter = questionAdapter
        viewBinding.recyclerViewQuestion.layoutManager =
            LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
*/
        return viewBinding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewBinding.btnMenteeAdd.setOnClickListener{
            Log.d("왜않되", "1")
            mContext!!.openFragment(6)
        }

        viewBinding.btnQuestionAdd.setOnClickListener {
            Log.d("왜않되", "1")
            mContext!!.openFragment(7)
        }

        viewBinding.btnMenteeInfo.setOnClickListener {
            mContext!!.openFragment(8)
        }
    }
}