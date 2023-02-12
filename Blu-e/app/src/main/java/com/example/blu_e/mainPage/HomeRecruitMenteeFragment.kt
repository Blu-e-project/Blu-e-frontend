package com.example.blu_e.mainPage
//멘티를 구하고 있어요!
import android.annotation.SuppressLint
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
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.data.mainPage.*
import com.example.blu_e.databinding.FragmentHomeRecruitMenteeBinding
import com.example.blu_e.mentoring.RequestMentoringActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRecruitMenteeFragment : Fragment() {
    lateinit var viewBinding: FragmentHomeRecruitMenteeBinding
    private lateinit var mContext: MainActivity
    private val api = RetroInterface.create() //retrofit 객체

    var menteeList: ArrayList<FindRecruitMenteeItem> = arrayListOf()

    companion object {
        fun newInstance(list: ArrayList<FindHotMenteeItem>, id: Int) =
            HomeRecruitMenteeFragment().apply {
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
        viewBinding = FragmentHomeRecruitMenteeBinding.inflate(layoutInflater)

        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //새로운 멘티가 있어요!
        viewBinding.backHome.setOnClickListener {
            mContext!!.openFragment(15)
        }
    }

    private fun loadData() { //멘티를 구하고 있어요
        api.findRecruitMentee ().enqueue(object : Callback<FindRecruitMenteeResponse> {
            @SuppressLint("SuspiciousIndentation")
            override fun onResponse(
                call: Call<FindRecruitMenteeResponse>,
                response: Response<FindRecruitMenteeResponse>
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
                            menteeList?.let { recruitMenteeAdapter(it) }

                            val adapter2 = RetrofitRecruitMenteeRVAdapter(menteeList, mContext)

                            adapter2.setItemClickListener(object: RetrofitRecruitMenteeRVAdapter.ItemClickListener{
                                override fun onClick(view: View, position: Int) {
                                    var intent = Intent(mContext, RequestMentoringActivity::class.java)
                                    intent.putExtra("pickId", "pickId")
                                }
                            })
                        }
                        else {
                            Log.d("문제", "내가 답한 질문이 없습니다.")
                        }
                    }
                }
            }
            override fun onFailure(call: Call<FindRecruitMenteeResponse>, t: Throwable) {
                Log.e("내가 답한 질문", "에러에러에러")
            }
        })
    }

    private fun recruitMenteeAdapter(resultList: ArrayList<FindRecruitMenteeItem>) {
        val adapter = RetrofitRecruitMenteeRVAdapter(resultList, mContext)
        viewBinding.recyclerViewMentee.adapter = adapter
        viewBinding.recyclerViewMentee.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        adapter.notifyItemChanged(resultList.size)
    }

/*

    override fun onResume() {
        super.onResume()

        val list: ArrayList<MenteeData> = arrayListOf()

        Log.e("로그", "데이터 추가")
        list.apply {
            add(MenteeData("매주 월요일 가능합니다.", "국어","23.01", "23.06", "온라인", "서울 성북구"))
            add(MenteeData("코딩 쉽게 같이 공부해요", "코딩", "23.01", "23.06", "온라인", "서울 성북구"))
            add(MenteeData("수능 수학 자신있습니다.","수학","23.01", "23.06", "온라인", "서울 성북구"))
            add(MenteeData("국어 문법 마스터", "국어","23.01", "23.06", "온라인", "서울 성북구"))
            add(MenteeData("매주 월요일 가능합니다.", "영어","23.01", "23.06", "온라인", "서울 성북구"))
            add(MenteeData("코딩 쉽게 같이 공부해요","국어","23.01", "23.06", "온라인", "서울 성북구"))
            add(MenteeData("국어 문법 마스터","코딩", "23.01", "23.06", "온라인", "서울 성북구"))
            add(MenteeData("매주 월요일 가능합니다.", "수학", "23.01", "23.06", "온라인", "서울 성북구"))
            add(MenteeData("코딩 쉽게 같이 공부해요","국어", "23.01", "23.06", "온라인", "서울 성북구"))
            add(MenteeData("매주 월요일 가능합니다.", "영어", "23.01", "23.06", "온라인", "서울 성북구"))
        }
        val adapter = MenteeDataRVAdapter(list)
        val grid = GridLayoutManager(context, 2)

        viewBinding.recyclerViewMentee.adapter = adapter
        viewBinding.recyclerViewMentee.layoutManager = grid
    }

*/
}

