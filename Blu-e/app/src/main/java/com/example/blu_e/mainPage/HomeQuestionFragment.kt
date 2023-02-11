package com.example.blu_e.mainPage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blu_e.MainActivity
import com.example.blu_e.MainApplication
import com.example.blu_e.data.QuestionData
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.data.mainPage.*
import com.example.blu_e.databinding.FragmentHomeQuestionBinding
import com.example.blu_e.mentoring.ProfileActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeQuestionFragment : Fragment() {
    lateinit var viewBinding: FragmentHomeQuestionBinding
    private lateinit var mContext: MainActivity

    private val api = RetroInterface.create() //retrofit 객체
    var problemList: ArrayList<Items> = arrayListOf()

    companion object {
        fun newInstance(list: ArrayList<FindFiveProblemItems>, id: Int) =
            HomeQuestionFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("list", list)
                    putInt("id", id)
                }
            }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentHomeQuestionBinding.inflate(layoutInflater)
        loadData()
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //새로운 멘티가 있어요!
        viewBinding.backHome.setOnClickListener {
            mContext!!.openFragment(14)
        }
    }
/*
    override fun onResume() {
        super.onResume()

        val list: ArrayList<QuestionData> = arrayListOf()

        list.apply {
            add(QuestionData("코딩", "이미지 첨부", "반복문(for문)", "조건이 왜 위와 같이 나오는지 모르겠어요!"))
            add(QuestionData("수학", "이미지 첨부", "확률과 통계", "이 문제가 왜 독립시행인지 이해가 안 돼요"))
            add(QuestionData("영어", "이미지 첨부", "to부정사", "모르겠어요.."))
            add(QuestionData("수학", "이미지 첨부", "덧셈", "모르겠어요.."))
            add(QuestionData("수학", "이미지 첨부", "미적분", "모르겠어요.."))
        }
        val questionAdapter = QuestionDataRVAdapter(list)
        val grid = GridLayoutManager(mContext, 2)

        viewBinding.recyclerViewQuestion.adapter = questionAdapter
        viewBinding.recyclerViewQuestion.layoutManager = grid
    }*/

    private fun loadData() {
        api.findProblems().enqueue(object : Callback<AllProblemsResponse> {
            override fun onResponse(
                call: Call<AllProblemsResponse>,
                response: Response<AllProblemsResponse>
            ) {
                val responseData = response.body() ?: return
                if (responseData != null) {
                    if (responseData.code == 1000) {
                        if (responseData.result != null) {
                            problemList?.addAll(responseData.result)
                            Log.e("멘토 구인글", "${problemList}")
                            problemList?.let { problemAdapter(it) }

                            val adapter2 = RetrofitProblemRVAdapter(problemList)

                            adapter2.setItemClickListener(object: RetrofitProblemRVAdapter.ItemClickListener{
                                override fun onClick(view: View, position: Int) {
                                    /*var detailFragment = HomeQuestionFragment.newInstance(problemList, position)
                                    mContext.supportFragmentManager.beginTransaction().replace(
                                        mContext.viewBinding.containerFragment.id, detailFragment
                                    ).commit()*/
                                }
                            })
                        } else {
                            Log.d("멘토 구인글", "멘토 구인글이 없어용")
                        }
                    }
                }
            }
            override fun onFailure(call: Call<AllProblemsResponse>, t: Throwable) {
                Log.e("멘토 구인글", "에러에러에러")
            }
        })
    }

    private fun problemAdapter(resultList: ArrayList<Items>) {
        val adapter = RetrofitProblemRVAdapter(resultList)
        viewBinding.recyclerViewQuestion.adapter = adapter
        viewBinding.recyclerViewQuestion.layoutManager = GridLayoutManager(mContext, 2)
        adapter.notifyItemChanged(resultList.size)
    }
}