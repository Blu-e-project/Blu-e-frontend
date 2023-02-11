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
import com.example.blu_e.MainActivity
import com.example.blu_e.RecruitMentorActivity
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.data.mainPage.*
import com.example.blu_e.databinding.FragmentHomeMenteeBinding
import com.example.blu_e.mentoring.AskQuestionActivity
import com.example.blu_e.mentoring.RegisterQuestionFormActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeMenteeFragment : Fragment() {
    private lateinit var mContext: MainActivity
    private lateinit var viewBinding: FragmentHomeMenteeBinding
    private lateinit var mentorAdapter: NewMentorDataRVAdapter //새로운 멘토가 있어요!
    private lateinit var recruitMenteeAdapter: MenteeDataRVAdapter //멘티를 구하고 있어요!

    private val api = RetroInterface.create() //retrofit 객체
    var mentorList: ArrayList<FindFiveMentorItems> = arrayListOf()
    var menteeList: ArrayList<FindHotMenteeItem> = arrayListOf()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentHomeMenteeBinding.inflate(layoutInflater)
        loadData1()
        loadData2()
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //새로운 멘토가 있어요!
        viewBinding.btnMentorAdd.setOnClickListener {
            mContext!!.openFragment(9)
        }
        //멘티를 구하고 있어요!
        viewBinding.btnRecruitMentee.setOnClickListener {
            mContext!!.openFragment(10)
        }
        //궁금한 문제가 있어요!
        viewBinding.btnAddProblem.setOnClickListener {
            val intent = Intent(activity, RegisterQuestionFormActivity::class.java)
            startActivity(intent)
        }
        //멘토를 구하고 있어요!
        viewBinding.btnMentorInfo.setOnClickListener {
            val intent = Intent(activity, RecruitMentorActivity::class.java)
            startActivity(intent)
        }
    }
    /*
    override fun onResume() {
        super.onResume()

        //새로운 멘토가 있어요!
        val newMentorList: ArrayList<NewMentorData> = arrayListOf()
        Log.e("홈", "데이터 들어옴?")

        newMentorList.apply {
            add(NewMentorData("금림"))
            add(NewMentorData("엘라"))
            add(NewMentorData("맨디"))
            add(NewMentorData("주디"))
            add(NewMentorData("융"))
            add(NewMentorData("시니"))
            add(NewMentorData("쫑"))
            add(NewMentorData("제이드"))
            add(NewMentorData("니케"))
            add(NewMentorData("그리드"))
        }
        mentorAdapter = NewMentorDataRVAdapter(newMentorList)
        viewBinding.recyclerViewNewMentee.adapter = mentorAdapter
        viewBinding.recyclerViewNewMentee.layoutManager =
            LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        Log.e("홈", "어댑터 연결됨?")

        //멘티를 구하고 있어요!
        val menteeList: ArrayList<MenteeData> = arrayListOf()

        menteeList.apply {
            add(MenteeData("매주 화요일에 멘토링 원해요", "국어", "23.01", "23.03", "온라인", "서울 성북구"))
            add(MenteeData("코딩이 너무 어려워요", "코딩", "23.01", "23.03", "온라인", "서울 금천구"))
            add(MenteeData("다정하고 친절하신 선생님...", "수학", "23.01", "23.03", "온라인", "서울 은평구"))
            add(MenteeData("비문학 쉽게 풀고 싶어요", "국어", "23.01", "23.03", "온라인", "서울 종로구"))
            add(MenteeData("매주 목요일에 멘토링 원해요", "영어", "23.01", "23.03", "온라인", "서울 강남구"))
            add(MenteeData("매주 화요일에 멘토링 원해요", "국어", "23.01", "23.03", "온라인", "서울 성북구"))
            add(MenteeData("코딩이 너무 어려워요", "코딩", "23.01", "23.03", "온라인", "서울 금천구"))
            add(MenteeData("다정하고 친절하신 선생님...", "수학", "23.01", "23.03", "온라인", "서울 은평구"))
            add(MenteeData("비문학 쉽게 풀고 싶어요", "국어", "23.01", "23.03", "온라인", "서울 종로구"))
            add(MenteeData("매주 목요일에 멘토링 원해요", "영어", "23.01", "23.03", "온라인", "서울 강남구"))
        }
        recruitMenteeAdapter = MenteeDataRVAdapter(menteeList)
        viewBinding.recyclerViewMentee.adapter = recruitMenteeAdapter
        viewBinding.recyclerViewMentee.layoutManager =
            LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
    }*/

    private fun loadData1() { //새로운 멘티가 있어요
        api.findFiveMentor ().enqueue(object : Callback<FindFiveMentorResponse> {
            @SuppressLint("SuspiciousIndentation")
            override fun onResponse(
                call: Call<FindFiveMentorResponse>,
                response: Response<FindFiveMentorResponse>
            ) {
                val responseData = response.body() ?: return
                Log.d("loadData1 목록 불러오기", "성공1")

                if (responseData != null) {
                    if (responseData.code == 1000) {
                        Log.d("loadData1 목록 불러오기", "성공2")

                        if (responseData.result != null) {
                            Log.d("loadData1 목록 불러오기", "성공3")

                            mentorList?.addAll(responseData.result)
                            Log.e("문제", "${mentorList}")
                            mentorList?.let { newMentorAdapter(it) }

                            val adapter2 = RetrofitHomeNewMentorRVAdapter(mentorList)

                            adapter2.setItemClickListener(object: RetrofitHomeNewMentorRVAdapter.ItemClickListener{
                                override fun onClick(view: View, position: Int) {
                                    var detailFragment = HomeNewMentorFragment.newInstance(mentorList, position)
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
            override fun onFailure(call: Call<FindFiveMentorResponse>, t: Throwable) {
                Log.e("내가 답한 질문", "에러에러에러")
            }
        })
    }

    private fun loadData2() { //멘티를 구하고 있어요
        api.findHotMentees().enqueue(object : Callback<FindHotMenteeResponse> {
            @SuppressLint("SuspiciousIndentation")
            override fun onResponse(
                call: Call<FindHotMenteeResponse>,
                response: Response<FindHotMenteeResponse>
            ) {
                val responseData = response.body() ?: return
                Log.d("loadData1 목록 불러오기", "성공1")

                if (responseData != null) {
                    if (responseData.code == 1000) {
                        Log.d("loadData1 목록 불러오기", "성공2")

                        if (responseData.result != null) {
                            Log.d("loadData1 목록 불러오기", "성공3")

                            menteeList?.addAll(responseData.result)
                            Log.e("문제", "${mentorList}")
                            menteeList?.let { recruitMenteeAdapter(it) }

                            val adapter2 = RetrofitHomeRecruitMenteeRVAdapter(menteeList)

                            adapter2.setItemClickListener(object: RetrofitHomeRecruitMenteeRVAdapter.ItemClickListener{
                                override fun onClick(view: View, position: Int) {
                                    var detailFragment = HomeRecruitMenteeFragment.newInstance(menteeList, position)
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
            override fun onFailure(call: Call<FindHotMenteeResponse>, t: Throwable) {
                Log.e("내가 답한 질문", "에러에러에러")
            }
        })
    }

    private fun newMentorAdapter(resultList: ArrayList<FindFiveMentorItems>) {
        val adapter = RetrofitHomeNewMentorRVAdapter(resultList)
        viewBinding.recyclerViewNewMentee.adapter = adapter
        viewBinding.recyclerViewNewMentee.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        adapter.notifyItemChanged(resultList.size)
    }

    private fun recruitMenteeAdapter(resultList: ArrayList<FindHotMenteeItem>) {
        val adapter = RetrofitHomeRecruitMenteeRVAdapter(resultList)
        viewBinding.recyclerViewMentee.adapter = adapter
        viewBinding.recyclerViewMentee.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        adapter.notifyItemChanged(resultList.size)
    }
}