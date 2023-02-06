package com.example.blu_e

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blu_e.customercenter.AccusationFragment
import com.example.blu_e.data.ListInMyPageAdapter
import com.example.blu_e.data.ListInMyPageData
import com.example.blu_e.databinding.FragmentMyPageBinding

class MyPageFragment : Fragment() {
    private lateinit var mContext: MainActivity
    private lateinit var viewBinding: FragmentMyPageBinding
    private lateinit var settingTitleList: ArrayList<ListInMyPageData>
    private lateinit var adapter: ListInMyPageAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMyPageBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        settingTitleList = ArrayList<ListInMyPageData>()
        var titleExample: ListInMyPageData
        for (i in 0..5) {
            titleExample = ListInMyPageData(i)
            settingTitleList.add(i, titleExample)
        }

        settingTitleList.get(0).settingTitle = "정보 수정"
        settingTitleList.get(1).settingTitle = "멘토링 내역"
        settingTitleList.get(2).settingTitle = "회원 신고"
        settingTitleList.get(3).settingTitle = "리뷰"
        settingTitleList.get(4).settingTitle = "내가 쓴 글/ 댓글 단 글"
        settingTitleList.get(5).settingTitle = "버전"

        adapter = ListInMyPageAdapter(settingTitleList)
        viewBinding.recyclerViewMypageSetting.adapter = adapter
        viewBinding.recyclerViewMypageSetting.layoutManager = LinearLayoutManager(mContext)

        adapter.setItemClickListener (object : ListInMyPageAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                val transaction = mContext.supportFragmentManager.beginTransaction()
                val intent = Intent(mContext, MentorHistoryActivity::class.java)
                when(position) {
                    0 -> transaction.replace(mContext.viewBinding.containerFragment.id, MentorChangeInfoFragment()).commit() //"멘토 정보 수정"
                    1 -> startActivity(intent)
                    2 -> transaction.replace(mContext.viewBinding.containerFragment.id, AccusationFragment()).commit() //"멘티 신고"
                    3 -> transaction.replace(mContext.viewBinding.containerFragment.id, MentorReviewFragment()).commit() //"멘티 리뷰"
                    4 -> "내가 쓴 글/ 댓글 단 글"
//                    5 -> "버전"
                }
            }
        })
    }

}