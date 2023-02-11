package com.example.blu_e.mainPage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.blu_e.MainActivity
import com.example.blu_e.MentorMyCommentActivity
import com.example.blu_e.RecruitMenteeActivity
import com.example.blu_e.databinding.FragmentHomeMentorButtonBinding
import com.example.blu_e.databinding.FragmentHomeNewMenteeBinding

class HomeMentorButtonFragment: Fragment() {
    private lateinit var viewBinding: FragmentHomeMentorButtonBinding
    private lateinit var mContext: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as MainActivity
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //새로운 멘티가 있어요!
        viewBinding.btnMyWriting.setOnClickListener {
            val intent = Intent(mContext, MentorMyCommentActivity::class.java)
            startActivity(intent)
        }
        viewBinding.btnRecruitMentor.setOnClickListener {
            mContext.openFragment(13)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentHomeMentorButtonBinding.inflate(layoutInflater)
        return viewBinding.root
    }
}