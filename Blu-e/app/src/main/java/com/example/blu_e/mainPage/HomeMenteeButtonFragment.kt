package com.example.blu_e.mainPage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.blu_e.*
import com.example.blu_e.databinding.FragmentHomeMenteeButtonBinding
import com.example.blu_e.databinding.FragmentHomeMentorBinding

class HomeMenteeButtonFragment : Fragment() {
    private lateinit var mContext: MainActivity
    private lateinit var viewBinding: FragmentHomeMenteeButtonBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as MainActivity
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //새로운 멘토가 있어요!
        viewBinding.btnMyWriting.setOnClickListener {
            val intent = Intent(mContext, MyPostCommentActivity::class.java)
            startActivity(intent)
        }
        viewBinding.btnRecruitMentee.setOnClickListener {
            val intent = Intent(mContext, RecruitMenteeActivity::class.java)
            startActivity(intent)
        }
    }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentHomeMenteeButtonBinding.inflate(layoutInflater)

        return viewBinding.root
    }
}