package com.example.blu_e.mainPage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.blu_e.MainActivity
import com.example.blu_e.data.mainPage.*
import com.example.blu_e.databinding.FragmentHomeNewMenteeBinding
import com.example.blu_e.login.SignUpActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeNewMenteeFragment : Fragment() {
    lateinit var viewBinding: FragmentHomeNewMenteeBinding
    private lateinit var mContext: MainActivity
    //private val api = RetroInterface.create() //retrofit 객체
    //private lateinit var qs: ArrayList<FindMenteesResponse.FindMenteeItem>

    companion object {
        fun newInstance(list: ArrayList<FindFiveMenteeResponse.FindFiveMenteeItems>, id: Int) = HomeNewMenteeFragment().apply {
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
        //loadData()
        return viewBinding.root
    }

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
    /*
    private fun loadData() {
        api.findMentees("eUItOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjIsImlhdCI6M..").enqueue(object : Callback<FindMenteesResponse> {
            override fun onResponse(
                call: Call<FindMenteesResponse>,
                response: Response<FindMenteesResponse>
            ) {
                if (response.isSuccessful) {
                    val body = response.body() ?: return
                    if (body.code == 1000) {
                        Log.d("목록 불러오기", "성공")
                        qs = body.result as ArrayList<FindMenteesResponse.FindMenteeItem>

                        val menteeAdapter = RetrofitHomeNewMenteeRVAdapter(qs)
                        val grid = GridLayoutManager(mContext, 5)

                        viewBinding.recyclerViewNewMentee.adapter = menteeAdapter
                        viewBinding.recyclerViewNewMentee.layoutManager = grid
                    }
                }
                else {
                    Log.d("새로운 멘티 리스트", "실패")
                }
            }
            override fun onFailure(call: Call<FindMenteesResponse>, t: Throwable) {
                Log.e("새로운 멘티 리스트", "failure")
            }
        })
    }
*/
}