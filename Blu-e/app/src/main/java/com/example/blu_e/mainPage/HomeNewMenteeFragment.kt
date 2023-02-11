package com.example.blu_e.mainPage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Profile
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
import com.example.blu_e.databinding.FragmentHomeNewMenteeBinding
import com.example.blu_e.mentoring.ProfileActivity
import com.example.blu_e.mentoring.RequestMentoringActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeNewMenteeFragment : Fragment() {
    lateinit var viewBinding: FragmentHomeNewMenteeBinding
    private lateinit var mContext: MainActivity
    private val api = RetroInterface.create() //retrofit 객체

    private lateinit var menteeList: ArrayList<FindMenteeItem>

    companion object {
        fun newInstance(list: ArrayList<FindFiveMenteeItems>, id: Int) = HomeNewMenteeFragment().apply {
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //새로운 멘티가 있어요!
        viewBinding.backHome.setOnClickListener {
            mContext!!.openFragment(14)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentHomeNewMenteeBinding.inflate(layoutInflater)
        loadData()
        return viewBinding.root
    }
/*
    override fun onResume() {
        super.onResume()

        val list: ArrayList<NewMenteeData> = arrayListOf()

        list.apply {
            add(NewMenteeData("금림"))
            add(NewMenteeData("엘라"))
            add(NewMenteeData("융"))
            add(NewMenteeData("니케"))
            add(NewMenteeData("맨디"))
        }
        val menteeAdapter = NewMenteeDataRVAdapter(list)
        val grid = GridLayoutManager(mContext, 5)

        viewBinding.recyclerViewNewMentee.adapter = menteeAdapter
        viewBinding.recyclerViewNewMentee.layoutManager = grid
    }
*/

    private fun loadData() {
        api.findMentees ().enqueue(object : Callback<FindMenteesResponse> {
            override fun onResponse(
                call: Call<FindMenteesResponse>,
                response: Response<FindMenteesResponse>
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

                            val adapter2 = RetrofitNewMenteeRVAdapter(menteeList)

                            adapter2.setItemClickListener(object: RetrofitNewMenteeRVAdapter.ItemClickListener{
                                override fun onClick(view: View, position: Int) {
                                    var intent = Intent(mContext, ProfileActivity::class.java)
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
            override fun onFailure(call: Call<FindMenteesResponse>, t: Throwable) {
                Log.e("내가 답한 질문", "에러에러에러")
            }
        })
    }

    private fun newMenteeAdapter(resultList: ArrayList<FindMenteeItem>) {
        val adapter = RetrofitNewMenteeRVAdapter(resultList)
        viewBinding.recyclerViewNewMentee.adapter = adapter
        viewBinding.recyclerViewNewMentee.layoutManager = GridLayoutManager(mContext, 5)
        adapter.notifyItemChanged(resultList.size)
    }
}