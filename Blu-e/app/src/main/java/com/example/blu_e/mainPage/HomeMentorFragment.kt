package com.example.blu_e.mainPage
//새로운 멘티가 있어요!
//궁금한 문제가 있어요!
//멘토를 구하고 있어요!
//멘티를 구해요
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blu_e.*
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.data.mainPage.*
import com.example.blu_e.databinding.FragmentHomeMentorBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeMentorFragment : Fragment() {
    private lateinit var mContext: MainActivity
    private lateinit var viewBinding: FragmentHomeMentorBinding
    private lateinit var questionAdapter: QuestionDataRVAdapter //궁금한 문제가 있어요!
    private lateinit var menteeAdapter: NewMenteeDataRVAdapter //새로운 멘티가 있어요!
    private lateinit var mentorAdapter: MentorDataRVAdapter //멘토를 구하고 있어요!

    private val api = RetroInterface.create() //retrofit 객체

    var menteeList: ArrayList<FindFiveMenteeItems> = arrayListOf()
    var problemList: ArrayList<FindFiveProblemItems> = arrayListOf()
    var mentorList: ArrayList<FindHotMentorItem> = arrayListOf()

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
        Log.e("홈", "들어왔나?")
        loadData1()
        loadData2()
        loadData3()

        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //새로운 멘티가 있어요!
        viewBinding.btnMenteeAdd.setOnClickListener {
            mContext!!.openFragment(6)
        }
        //궁금한 문제가 있어요!
        viewBinding.btnQuestionAdd.setOnClickListener {
            mContext!!.openFragment(11)
        }
        //멘토를 구하고 있어요!
        viewBinding.btnMentorAdd.setOnClickListener {
            mContext!!.openFragment(7)
        }
        //멘티를 구해요
        viewBinding.btnMenteeInfo.setOnClickListener {
            val intent = Intent(activity, RecruitMenteeActivity::class.java)
            Log.e("멘티를 구해요", "들어가나염?")
            startActivity(intent)
        }
    }
/*

    //더미 데이터
    override fun onResume() {
        super.onResume()

        //새로운 멘티가 있어요!
        val newMenteeList: ArrayList<NewMenteeData> = arrayListOf()
        Log.e("홈", "데이터 들어옴?")

        newMenteeList.apply {
            add(NewMenteeData("금림"))
            add(NewMenteeData("엘라"))
            add(NewMenteeData("맨디"))
            add(NewMenteeData("주디"))
            add(NewMenteeData("융"))
        }
        menteeAdapter = NewMenteeDataRVAdapter(newMenteeList)
        viewBinding.recyclerViewHomeNewMentee.adapter = menteeAdapter
        viewBinding.recyclerViewHomeNewMentee.layoutManager =
            LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        Log.e("홈", "어댑터 연결됨?")

        //궁금한 문제가 있어요!
        val questionList: ArrayList<QuestionData> = arrayListOf()

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

        //멘토를 구하고 있어요!
        val menteeList: ArrayList<MentorData> = arrayListOf()

        menteeList.apply {
            add(MentorData("매주 화요일에 멘토링 원해요", "국어", "23.01", "23.03", "온라인", "서울 성북구"))
            add(MentorData("코딩이 너무 어려워요", "코딩", "23.01", "23.03", "온라인", "서울 금천구"))
            add(MentorData("다정하고 친절하신 선생님...", "수학", "23.01", "23.03", "온라인", "서울 은평구"))
            add(MentorData("비문학 쉽게 풀고 싶어요", "국어", "23.01", "23.03", "온라인", "서울 종로구"))
            add(MentorData("매주 목요일에 멘토링 원해요", "영어", "23.01", "23.03", "온라인", "서울 강남구"))
        }

        mentorAdapter = MentorDataRVAdapter(menteeList)
        viewBinding.recyclerViewMentor.adapter = mentorAdapter
        viewBinding.recyclerViewMentor.layoutManager =
            LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
    }
*/

    private fun loadData1() { //새로운 멘티가 있어요
        api.findFiveMentee().enqueue(object : Callback<FindFiveMenteeResponse> {
            @SuppressLint("SuspiciousIndentation")
            override fun onResponse(
                call: Call<FindFiveMenteeResponse>,
                response: Response<FindFiveMenteeResponse>
            ) {
                val responseData = response.body() ?: return
                Log.d("loadData1 목록 불러오기", "성공1")

                if (responseData != null) {
                    if (responseData.code == 1000) {
                        Log.d("loadData1 목록 불러오기", "성공2")

                        if (responseData.result != null) {
                            Log.d("loadData1 목록 불러오기", "성공3")

                            menteeList?.addAll(responseData.result)
                            Log.e("문제", "${menteeList}")
                            menteeList?.let { newMenteeAdapter(it) }

                            val adapter2 = RetrofitHomeNewMenteeRVAdapter(menteeList)

                                adapter2.setItemClickListener(object: RetrofitHomeNewMenteeRVAdapter.ItemClickListener{
                                override fun onClick(view: View, position: Int) {
                                    var detailFragment = HomeNewMenteeFragment.newInstance(menteeList, position)
                                    mContext.supportFragmentManager.beginTransaction().replace(
                                        mContext.viewBinding.containerFragment.id, detailFragment
                                    ).commit()
                                }
                            })
                        }
                        else {
                            Log.d("문제", "내가 답한 질문이 없습니다.")
                        }
                    }
                }
            }
            override fun onFailure(call: Call<FindFiveMenteeResponse>, t: Throwable) {
                Log.e("내가 답한 질문", "에러에러에러")
            }
        })
    }

    private fun loadData2() { //궁금한 문제가 있어요!
        api.findFiveProblem().enqueue(object : Callback<FindFiveProblemResponse> {
            override fun onResponse(
                call: Call<FindFiveProblemResponse>,
                response: Response<FindFiveProblemResponse>
            ) {
                val responseData = response.body() ?: return
                if (responseData != null) {
                    if (responseData.code == 1000) {
                        if (responseData.result != null) {
                            problemList?.addAll(responseData.result)
                            Log.e("멘토 구인글", "${problemList}")
                            problemList?.let { problemAdapter(it) }

                            val adapter2 = RetrofitHomeProblemRVAdapter(problemList)

                            adapter2.setItemClickListener(object: RetrofitHomeProblemRVAdapter.ItemClickListener{
                                override fun onClick(view: View, position: Int) {
                                    var detailFragment = HomeQuestionFragment.newInstance(problemList, position)
                                    mContext.supportFragmentManager.beginTransaction().replace(
                                        mContext.viewBinding.containerFragment.id, detailFragment
                                    ).commit()
                                }
                            })
                        } else {
                            Log.d("멘토 구인글", "멘토 구인글이 없어용")
                        }
                    }
                }
            }
            override fun onFailure(call: Call<FindFiveProblemResponse>, t: Throwable) {
                Log.e("멘토 구인글", "에러에러에러")
            }
        })
    }

    private fun loadData3() { //새로운 멘티가 있어요
        api.findHotMentors().enqueue(object : Callback<FindHotMentorResponse> {
            override fun onResponse(
                call: Call<FindHotMentorResponse>,
                response: Response<FindHotMentorResponse>
            ) {
                val responseData = response.body() ?: return
                if (responseData != null) {
                    if (responseData.code == 1000) {
                        if (responseData.result != null) {
                            mentorList?.addAll(responseData.result)
                            Log.e("멘토 구인글", "${mentorList}")
                            mentorList?.let { recruitMentorAdapter(it) }

                            val adapter2 = RetrofitHomeRecruitMentorRVAdapter(mentorList, mContext)

                            adapter2.setItemClickListener(object: RetrofitHomeRecruitMentorRVAdapter.ItemClickListener{
                                override fun onClick(view: View, position: Int) {
                                    var detailFragment = HomeRecruitMentorFragment.newInstance(mentorList, position)
                                    mContext.supportFragmentManager.beginTransaction().replace(
                                        mContext.viewBinding.containerFragment.id, detailFragment
                                    ).commit()
                                }
                            })
                        } else {
                            Log.d("멘토 구인글", "멘토 구인글이 없어용")
                        }
                    }
                }
            }
            override fun onFailure(call: Call<FindHotMentorResponse>, t: Throwable) {
                Log.e("멘토 구인글", "에러에러에러")
            }
        })
    }

    private fun newMenteeAdapter(resultList: ArrayList<FindFiveMenteeItems>) {
        val adapter = RetrofitHomeNewMenteeRVAdapter(resultList)
        viewBinding.recyclerViewHomeNewMentee.adapter = adapter
        viewBinding.recyclerViewHomeNewMentee.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        adapter.notifyItemChanged(resultList.size)
    }

    private fun problemAdapter(resultList: ArrayList<FindFiveProblemItems>) {
        val adapter = RetrofitHomeProblemRVAdapter(resultList)
        viewBinding.recyclerViewQuestion.adapter = adapter
        viewBinding.recyclerViewQuestion.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        adapter.notifyItemChanged(resultList.size)
    }

    private fun recruitMentorAdapter(resultList: ArrayList<FindHotMentorItem>) {
        val adapter = RetrofitHomeRecruitMentorRVAdapter(resultList, mContext)
        viewBinding.recyclerViewMentor.adapter = adapter
        viewBinding.recyclerViewMentor.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        adapter.notifyItemChanged(resultList.size)
    }
}
