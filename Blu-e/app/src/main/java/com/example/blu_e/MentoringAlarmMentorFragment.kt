package com.example.blu_e

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blu_e.data.MentorAlarmData
import com.example.blu_e.data.MentorAlarmDataRVAdapter
import com.example.blu_e.databinding.FragmentMentorAlarmBinding

class MentoringAlarmMentorFragment : Fragment() {
    private lateinit var mContext: MainActivity
    lateinit var viewBinding: FragmentMentorAlarmBinding


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMentorAlarmBinding.inflate(inflater, container, false)
        return viewBinding.root

        val dataList: ArrayList<MentorAlarmData> = arrayListOf()
        val dataRVAdapter = MentorAlarmDataRVAdapter(dataList)

        dataList.apply {
            add(MentorAlarmData("엘라","멘티 신청","엘라 멘티님이 멘티 신청을 하였습니다"))
            add(MentorAlarmData("금림","매칭 성공","금림 멘토님과 매칭이 완료되었습니다."))
            add(MentorAlarmData("금림","멘티 신청","금림 멘티님이 멘티 신청을 하였습니다"))
        }


        dataRVAdapter.notifyItemInserted(2)
        viewBinding.rvDataAlarm.adapter=dataRVAdapter
        viewBinding.rvDataAlarm.layoutManager=LinearLayoutManager(getContext())
    }

}