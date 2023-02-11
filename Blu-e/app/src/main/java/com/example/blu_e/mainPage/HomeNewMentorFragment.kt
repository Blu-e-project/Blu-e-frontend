package com.example.blu_e.mainPage

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
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.data.mainPage.*
import com.example.blu_e.databinding.FragmentHomeNewMentorBinding
import com.example.blu_e.mentoring.ProfileActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeNewMentorFragment : Fragment() {
    lateinit var viewBinding: FragmentHomeNewMentorBinding
    private lateinit var mContext: MainActivity

    private val api = RetroInterface.create() //retrofit 객체
    private lateinit var mentorList: ArrayList<FindMentorItem>

    companion object {
        fun newInstance(list: ArrayList<FindFiveMentorItems>, id: Int) =
            HomeNewMentorFragment().apply {
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
        viewBinding = FragmentHomeNewMentorBinding.inflate(layoutInflater)
        loadData()
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
/*
        val list: ArrayList<NewMentorData> = arrayListOf()

        list.apply {
            add(NewMentorData("금림"))
            add(NewMentorData("엘라"))
            add(NewMentorData("융"))
            add(NewMentorData("니케"))
            add(NewMentorData("맨디"))
            add(NewMentorData("제이드"))
            add(NewMentorData("주디"))
            add(NewMentorData("시니"))
            add(NewMentorData("쫑"))
            add(NewMentorData("그리드"))
        }
        val mentorAdapter = NewMentorDataRVAdapter(list)
        viewBinding.recyclerViewNewMentor.adapter = mentorAdapter
        viewBinding.recyclerViewNewMentor.layoutManager = GridLayoutManager(mContext, 5)

        val intent = Intent(mContext, ProfileActivity::class.java)
        intent.putExtra("userId", "userId")*/
    }

    override fun onResume() {
        super.onResume()

        //새로운 멘티가 있어요!
        viewBinding.backHome.setOnClickListener {
            mContext!!.openFragment(15)
        }
    }
    private fun loadData() { //새로운 멘티가 있어요
        api.findMentors ().enqueue(object : Callback<FindMentorsResponse> {
            @SuppressLint("SuspiciousIndentation")
            override fun onResponse(
                call: Call<FindMentorsResponse>,
                response: Response<FindMentorsResponse>
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

                            val adapter2 = RetrofitNewMentorRVAdapter(mentorList)

                            adapter2.setItemClickListener(object: RetrofitNewMentorRVAdapter.ItemClickListener{
                                override fun onClick(view: View, position: Int) {
                                    val intent = Intent(mContext, ProfileActivity::class.java)
                                    intent.putExtra("userId", "userId")
                                }
                            })
                        }
                        else {
                            Log.d("문제", "내가 답한 질문이 없습니다.")
                        }
                    }
                }
            }
            override fun onFailure(call: Call<FindMentorsResponse>, t: Throwable) {
                Log.e("내가 답한 질문", "에러에러에러")
            }
        })
    }
    private fun newMentorAdapter(resultList: ArrayList<FindMentorItem>) {
        val adapter = RetrofitNewMentorRVAdapter(resultList)
        viewBinding.recyclerViewNewMentor.adapter = adapter
        viewBinding.recyclerViewNewMentor.layoutManager = GridLayoutManager(mContext, 5)
        adapter.notifyItemChanged(resultList.size)
    }


}