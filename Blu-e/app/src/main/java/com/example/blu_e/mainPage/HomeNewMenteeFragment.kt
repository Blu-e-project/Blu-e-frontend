package com.example.blu_e.mainPage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.blu_e.MainActivity
import com.example.blu_e.data.*
import com.example.blu_e.databinding.FragmentHomeNewMenteeBinding

class HomeNewMenteeFragment : Fragment() {
    lateinit var viewBinding: FragmentHomeNewMenteeBinding
    private lateinit var mContext: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as MainActivity
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
    }
}