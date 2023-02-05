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
import com.example.blu_e.data.MenteeData
import com.example.blu_e.databinding.FragmentHomeRecruitMenteeBinding

class HomeRecruitMenteeFragment : Fragment() {
    lateinit var viewBinding: FragmentHomeRecruitMenteeBinding
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
        viewBinding = FragmentHomeRecruitMenteeBinding.inflate(layoutInflater)

        return viewBinding.root
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
}

