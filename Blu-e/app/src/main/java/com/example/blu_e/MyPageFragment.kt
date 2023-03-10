package com.example.blu_e

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blu_e.customercenter.AccusationFragment
import com.example.blu_e.data.ListInMyPageAdapter
import com.example.blu_e.data.ListInMyPageData
import com.example.blu_e.databinding.FragmentMyPageBinding
import com.example.blu_e.login.LoginActivity
import com.example.blu_e.mypage.*

class MyPageFragment : Fragment() {
    private var role = MainApplication.prefs.getString("role", "")
    var isMentor: Int = role.toInt() //멘토: 1, 멘티: 2
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
        for (i in 0..8) {
            titleExample = ListInMyPageData(i)
            settingTitleList.add(i, titleExample)
        }

        settingTitleList.get(0).settingTitle = "정보 수정"
        settingTitleList.get(1).settingTitle = "멘토링 내역"
        settingTitleList.get(2).settingTitle = "회원 신고"
        settingTitleList.get(3).settingTitle = "리뷰"
        settingTitleList.get(4).settingTitle = "내가 쓴 글/ 댓글 단 글"
        settingTitleList.get(5).settingTitle = "내가 쓴 리뷰"
        settingTitleList.get(6).settingTitle = "버전"
        settingTitleList.get(7).settingTitle = "로그아웃"
        settingTitleList.get(8).settingTitle = "비밀번호 변경"

        adapter = ListInMyPageAdapter(settingTitleList)
        viewBinding.recyclerViewMypageSetting.adapter = adapter
        viewBinding.recyclerViewMypageSetting.layoutManager = LinearLayoutManager(mContext)

        adapter.setItemClickListener (object : ListInMyPageAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                val transaction = mContext.supportFragmentManager.beginTransaction()
                val chagngeinfo = Intent(mContext, ChangeInfoActivity::class.java)
                val history = Intent(mContext, MentorHistoryActivity::class.java)
                val aboutme_mentor = Intent(mContext,MentorAboutMeReviewActivity::class.java)
                val myreview = Intent(mContext, MentorMyReviewActivity::class.java)
                val intent4 = Intent(mContext,MyPostCommentActivity::class.java)
                when(position) {
                    0 ->  startActivity(chagngeinfo)//"멘토 정보 수정"
                    1 -> startActivity(history) //멘토링 내역
                    2 -> transaction.replace(mContext.viewBinding.containerFragment.id, AccusationFragment()).commit() //"멘티 신고"
                    3 -> startActivity(aboutme_mentor) //"나에 대한 리뷰"
                    4 -> startActivity(intent4)
                    5 -> startActivity(myreview) //내가 쓴 리뷰
                    6 -> "버전"
                    7 -> logoutDialog()
                    8 -> transaction.replace(mContext.viewBinding.containerFragment.id, MentorPasswdChangeFragment()).commit() //비밀번호 수정
                }

            }
        })

    }
    fun logoutDialog(){
        val builder = AlertDialog.Builder(mContext)
            .setTitle("로그아웃")
            .setMessage("로그아웃하시겠습니까?")
            .setPositiveButton("네",
                DialogInterface.OnClickListener{ dialog, which ->
                    Toast.makeText(mContext, "로그아웃", Toast.LENGTH_SHORT).show()
                    //
//                    MainApplication.prefs.edit // 여기서 Shared Preference 를 remove 한다!
                    MainApplication.prefs.remove("blu-e-access-token")
                    MainApplication.prefs.remove("userId")
                    MainApplication.prefs.remove("role")
                    MainApplication.prefs.commit() // SP 삭제되는 것을 확인

//                    Log.d("로그아웃", "${ MainApplication.prefs.getString("blu-e-access-token", "")}")
//                    Log.d("로그아웃", "${ MainApplication.prefs.getString("userId", "")}")
//                    Log.d("로그아웃", "${ MainApplication.prefs.getString("role", "")}")
                    val intent = Intent(activity, LoginActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) //스택 위에 것지우기
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    startActivity(intent)
                })
            .setNegativeButton("아니요",
                DialogInterface.OnClickListener{ dialog, which ->
//                    Toast.makeText(mContext, "확인", Toast.LENGTH_SHORT).show()
//                    //어느화면으로 이동할지
                })
        builder.show()
    }
}