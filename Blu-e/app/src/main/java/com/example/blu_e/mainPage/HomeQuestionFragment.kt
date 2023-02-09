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
    private lateinit var list: ArrayList<AllProblemsResponse.Items>
    private lateinit var adapter: RetrofitProblemRVAdapter

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

    override fun onResume() {
        super.onResume()

        val list: ArrayList<QuestionData> = arrayListOf()

        list.apply {
            add(QuestionData("매주 화요일에 멘토링 원해요", "국어", "23.01", "23.03"))
            add(QuestionData("코딩이 너무 어려워요", "코딩", "23.01", "23.03"))
            add(QuestionData("다정하고 친절하신 선생님...", "수학", "23.01", "23.03"))
            add(QuestionData("비문학 쉽게 풀고 싶어요", "국어", "23.01", "23.03"))
            add(QuestionData("매주 목요일에 멘토링 원해요", "영어", "23.01", "23.03"))
            add(QuestionData("매주 화요일에 멘토링 원해요", "국어", "23.01", "23.03"))
            add(QuestionData("코딩이 너무 어려워요", "코딩", "23.01", "23.03"))
            add(QuestionData("다정하고 친절하신 선생님...", "수학", "23.01", "23.03"))
            add(QuestionData("비문학 쉽게 풀고 싶어요", "국어", "23.01", "23.03"))
            add(QuestionData("매주 목요일에 멘토링 원해요", "영어", "23.01", "23.03"))
        }
        val questionAdapter = QuestionDataRVAdapter(list)
        val grid = GridLayoutManager(mContext, 2)

        viewBinding.recyclerViewQuestion.adapter = questionAdapter
        viewBinding.recyclerViewQuestion.layoutManager = grid
    }

    private fun loadData() {
        api.findProblems ().enqueue(object :
            Callback<AllProblemsResponse> {
            override fun onResponse(
                call: Call<AllProblemsResponse>,
                response: Response<AllProblemsResponse>
            ) {
                if (response.isSuccessful) {
                    val body = response.body() ?: return
                    if (body.code == 1000) {
                        Log.d("목록 불러오기", "성공")
                        list = body.result as ArrayList<AllProblemsResponse.Items>
                        adapter = RetrofitProblemRVAdapter(list)

                        viewBinding.recyclerViewQuestion.adapter = adapter
                        viewBinding.recyclerViewQuestion.layoutManager = LinearLayoutManager(mContext)
                        adapter.notifyItemChanged(list.size)

                        val intent = Intent(mContext, ProfileActivity::class.java)
                        intent.putExtra("userId", "userId")
                    }
                }
                else {
                    Log.d("새로운 멘토 리스트", "실패")
                }
            }
            override fun onFailure(call: Call<AllProblemsResponse>, t: Throwable) {
                Log.e("새로운 멘토 리스트", "failure")
            }
        })
    }
}