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
import com.example.blu_e.MainActivity
import com.example.blu_e.MainApplication
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.data.mainPage.FindMentorsResponse
import com.example.blu_e.data.mainPage.NewMentorData
import com.example.blu_e.data.mainPage.RetrofitNewMentorRVAdapter
import com.example.blu_e.databinding.FragmentHomeNewMentorBinding
import com.example.blu_e.mentoring.ProfileActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeNewMentorFragment : Fragment() {
    lateinit var viewBinding: FragmentHomeNewMentorBinding
    private lateinit var mContext: MainActivity

    private val api = RetroInterface.create() //retrofit 객체
    private lateinit var list: ArrayList<FindMentorsResponse.FindMentorsItem>

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
        //loadData()
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

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
        intent.putExtra("userId", "userId")
    }

    override fun onResume() {
        super.onResume()

        //새로운 멘티가 있어요!
        viewBinding.backHome.setOnClickListener {
            mContext!!.openFragment(15)
        }

        api.findMentors().enqueue(object :
            Callback<FindMentorsResponse> {
            override fun onResponse(
                call: Call<FindMentorsResponse>,
                response: Response<FindMentorsResponse>
            ) {
                if (response.isSuccessful) {
                    val body = response.body() ?: return
                    if (body.code == 1000) {
                        Log.d("목록 불러오기", "성공")
                        list = body.result as ArrayList<FindMentorsResponse.FindMentorsItem>

                        val menteeAdapter = RetrofitNewMentorRVAdapter(list)
                        val grid = GridLayoutManager(mContext, 5)

                        viewBinding.recyclerViewNewMentor.adapter = menteeAdapter
                        viewBinding.recyclerViewNewMentor.layoutManager = grid

                        val intent = Intent(mContext, ProfileActivity::class.java)
                        intent.putExtra("userId", "userId")
                    }
                }
                else {
                    Log.d("새로운 멘토 리스트", "실패")
                }
            }
            override fun onFailure(call: Call<FindMentorsResponse>, t: Throwable) {
                Log.e("새로운 멘토 리스트", "failure")
            }
        })
    }


}