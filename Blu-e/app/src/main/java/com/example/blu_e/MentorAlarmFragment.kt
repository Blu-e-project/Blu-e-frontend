package com.example.blu_e

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blu_e.data.MentorAlarmData
import com.example.blu_e.data.MentorAlarmDataRVAdapter
import com.example.blu_e.databinding.FragmentMentorAlarmBinding

class MentorAlarmFragment : Fragment() {

    private lateinit var mContext: MainActivity
    lateinit var viewBinding: FragmentMentorAlarmBinding
    private lateinit var dataList : ArrayList<MentorAlarmData>
    private lateinit var adapter: MentorAlarmDataRVAdapter


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMentorAlarmBinding.inflate(inflater, container, false)
        return viewBinding.root}

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dataList = ArrayList<MentorAlarmData>()
        var alarmData: MentorAlarmData
        for (i in 0..2) {
            alarmData = MentorAlarmData(i)
            dataList.add(i, alarmData)
        }
        dataList.get(0).mentee = "엘라"
        dataList.get(0).mathcing = "멘티 신청"
        dataList.get(0).mathcing2 = "엘라 멘티님이 멘티 신청을 하였습니다"
        dataList.get(1).mentee = "금림"
        dataList.get(1).mathcing = "매칭 성공"
        dataList.get(1).mathcing2 = "금림 멘티님과 매칭이 완료되었습니다"
        dataList.get(2).mentee = "금림"
        dataList.get(2).mathcing = "멘티 신청"
        dataList.get(2).mathcing2 = "금림 멘티님이 멘티 신청을 하였습니다"

        adapter = MentorAlarmDataRVAdapter(dataList)
        viewBinding.rvDataAlarm.adapter = adapter
        viewBinding.rvDataAlarm.layoutManager = LinearLayoutManager(mContext)
        /*

        val dataRVAdapter = MentorAlarmDataRVAdapter(dataList)
        //dataRVAdapter.notifyItemInserted(2)

        val linear = LinearLayoutManager(context)

        viewBinding.rvDataAlarm.adapter=dataRVAdapter
        viewBinding.rvDataAlarm.layoutManager=LinearLayoutManager(this.context)*/
    }
}