package com.example.blu_e.mainPage

import android.content.Context
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
import com.example.blu_e.data.mainPage.FindMenteesResponse
import com.example.blu_e.data.mainPage.FindMentorsResponse
import com.example.blu_e.data.mainPage.NewMenteeData
import com.example.blu_e.databinding.FragmentHomeNewMenteeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeNewMenteeFragment : Fragment() {
    lateinit var viewBinding: FragmentHomeNewMenteeBinding
    private lateinit var mContext: MainActivity
    private val api = RetroInterface.create() //retrofit 객체
    private lateinit var qs: ArrayList<FindMenteesResponse.FindMenteeItem>

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
            add(NewMenteeData("제이드"))
            add(NewMenteeData("주디"))
            add(NewMenteeData("시니"))
            add(NewMenteeData("쫑"))
            add(NewMenteeData("그리드"))
        }
        val menteeAdapter = NewMenteeDataRVAdapter(list)
        val grid = GridLayoutManager(mContext, 5)

        viewBinding.recyclerViewNewMentee.adapter = menteeAdapter
        viewBinding.recyclerViewNewMentee.layoutManager = grid

/*

        api.findMentees("eUItOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjIsImlhdCI6M..").enqueue(object:
            Callback<FindMenteesResponse> {
             override fun onResponse(call: Call<FindMenteesResponse>, response: Response<FindMenteesResponse>) {
                 val body = response.body() ?: return
                 if(body.code == 1000) {
                     Log.d("멘티 리스트 불러오기", "성공")
                     qs = body.result as ArrayList<FindMenteesResponse.FindMenteeItem>
                     adapter2 = NewMenteeDataRVAdapter(qs)
                     viewBinding.recyclerViewNewMentee.adapter = adapter2
                     viewBinding.recyclerViewNewMentee.layoutManager = LinearLayoutManager(mContext)
                     adapter2.setItemClickListener(object: NewMenteeDataRVAdapter.ItemClickListener{
                         override fun onClick(view: View, position: Int) {
                             var detailFragment = QuestionDetailFragment.newInstance(qs, position)
                             mContext.supportFragmentManager.beginTransaction().replace(
                                 mContext.viewBinding.containerFragment.id, detailFragment
                             ).commit()
                         }
                     })
                 }
                 else {
                     Log.d("멘티 리스트 불러오기", "실패")
                 }
             }
             override fun onFailure(call: Call<FindMenteesResponse>, t: Throwable) {
                 //실패시
                 Log.d("멘티 리스트 불러오기", "실패")
             }
         }) */
    }
}