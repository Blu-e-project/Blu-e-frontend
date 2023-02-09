package com.example.blu_e.mainPage
//멘티를 구하고 있어요!
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.blu_e.MainActivity
import com.example.blu_e.data.mainPage.FindRecruitMenteeResponse
import com.example.blu_e.data.mainPage.MenteeData
import com.example.blu_e.data.mainPage.RetrofitRecruitMenteeRVAdapter
import com.example.blu_e.databinding.FragmentHomeRecruitMenteeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRecruitMenteeFragment : Fragment() {
    lateinit var viewBinding: FragmentHomeRecruitMenteeBinding
    private lateinit var mContext: MainActivity
    //private val api = RetroInterface.create() //retrofit 객체

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
/*
    private fun loadData() {
        api.findHotMentees ("eUItOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjIsImlhdCI6M..").enqueue(object :
            Callback<FindRecruitMenteeResponse> {
            override fun onResponse(
                call: Call<FindRecruitMenteeResponse>,
                response: Response<FindRecruitMenteeResponse>
            ) {
                if (response.isSuccessful) {
                    val body = response.body() ?: return
                    if (body.code == 1000) {
                        Log.d("목록 불러오기", "성공")
                        val list = body.result as ArrayList<FindRecruitMenteeResponse.FindRecruitMenteeItem>

                        val menteeAdapter = RetrofitRecruitMenteeRVAdapter(list)
                        val grid = GridLayoutManager(mContext, 2)

                        viewBinding.recyclerViewMentee.adapter = menteeAdapter
                        viewBinding.recyclerViewMentee.layoutManager = grid

                        menteeAdapter.setItemClickListener(object: RetrofitRecruitMenteeRVAdapter.ItemClickListener{
                            override fun onClick(view: View, position: Int) {

                            }
                        })
                    }
                }
                else {
                    Log.d("새로운 멘토 리스트", "실패")
                }
            }
            override fun onFailure(call: Call<FindRecruitMenteeResponse>, t: Throwable) {
                Log.e("새로운 멘토 리스트", "failure")
            }
        })
    }*/
}

