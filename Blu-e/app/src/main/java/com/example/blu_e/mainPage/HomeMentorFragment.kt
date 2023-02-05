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
import com.example.blu_e.customercenter.FaqDetailFragment
import com.example.blu_e.customercenter.QuestionDetailFragment
import com.example.blu_e.customercenter.QuestionDetailFragment.Companion.newInstance
import com.example.blu_e.data.*
import com.example.blu_e.databinding.FragmentHomeMentorBinding
import com.example.blu_e.databinding.FragmentHomeRecruitMentorBinding

class HomeMentorFragment : Fragment() {
    private lateinit var mContext: MainActivity
    private lateinit var viewBinding: FragmentHomeMentorBinding
    private lateinit var questionList: ArrayList<QuestionData>
    private lateinit var menteeList: ArrayList<MenteeData>
    private lateinit var questionAdapter: QuestionDataRVAdapter //궁금한 문제가 있어요!
    private lateinit var menteeAdapter: MenteeDataRVAdapter //새로운 멘티가 있어요!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):  View? {
        viewBinding = FragmentHomeMentorBinding.inflate(inflater, container, false)

        return viewBinding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //새로운 멘티가 있어요!
        menteeList.apply {
            add(MenteeData("금림"))
            add(MenteeData("엘라"))
            add(MenteeData("맨디"))
            add(MenteeData("주디"))
            add(MenteeData("융"))
            add(MenteeData("시니"))
            add(MenteeData("쫑"))
            add(MenteeData("제이드"))
            add(MenteeData("니케"))
            add(MenteeData("그리드"))
        }

        menteeAdapter = MenteeDataRVAdapter(menteeList)
        viewBinding.recyclerViewNewMentee.adapter = menteeAdapter
        viewBinding.recyclerViewNewMentee.layoutManager =
            LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)

        menteeAdapter.setItemClickListener(object : MenteeDataRVAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                var menteeFragment = HomeNewMenteeFragment.newInstance(menteeList, position)
                mContext.supportFragmentManager.beginTransaction().replace(
                    mContext.viewBinding.containerFragment.id, menteeFragment
                ).commit()
            }
        })
/*
        //궁금한 문제가 있어요!
        questionList.apply {
            add(QuestionData("코딩", "이미지 첨부", "반복문(for문)", "조건이 왜 위와 같이 나오는지 모르겠어요!"))
            add(QuestionData("수학", "이미지 첨부", "확률과 통계", "이 문제가 왜 독립시행인지 이해가 안 돼요"))
            add(QuestionData("영어", "이미지 첨부", "to부정사", "모르겠어요.."))
            add(QuestionData("수학", "이미지 첨부", "덧셈", "모르겠어요.."))
            add(QuestionData("수학", "이미지 첨부", "미적분", "모르겠어요.."))
        }

        questionAdapter = QuestionDataRVAdapter(questionList)
        viewBinding.recyclerViewQuestion.adapter = questionAdapter
        viewBinding.recyclerViewQuestion.layoutManager =
            LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)

        questionAdapter.setItemClickListener(object : QuestionDataRVAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                var questionFragment = HomeQuestionFragment.newInstance(questionList, position)
                mContext.supportFragmentManager.beginTransaction().replace(
                    mContext.viewBinding.containerFragment.id, questionFragment
                ).commit()
            }
        })

*/

/*
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
*/
    }
}