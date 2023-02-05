package com.example.blu_e.mainPage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.blu_e.MainActivity
import com.example.blu_e.data.NewMentorData
import com.example.blu_e.databinding.FragmentHomeNewMentorBinding

class HomeNewMentorFragment : Fragment() {
    lateinit var viewBinding: FragmentHomeNewMentorBinding
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
        viewBinding = FragmentHomeNewMentorBinding.inflate(layoutInflater)

        return viewBinding.root
    }

    override fun onResume() {
        super.onResume()

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
        val grid = GridLayoutManager(mContext, 5)

        viewBinding.recyclerViewNewMentor.adapter = mentorAdapter
        viewBinding.recyclerViewNewMentor.layoutManager = grid
    }
}