package com.example.blu_e

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.blu_e.databinding.FragmentMentorChangeInfoBinding

class MentorChangeInfoFragment : Fragment() {
    private lateinit var mContext: MainActivity
    private lateinit var viewBinding: FragmentMentorChangeInfoBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMentorChangeInfoBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewBinding.backToCenterAMentor.setOnClickListener{
            mContext!!.openFragment(5)
        }

        viewBinding.checkNicknameBtnMentor.setOnClickListener {
            val builder = AlertDialog.Builder(mContext)
            builder
                .setTitle("닉네임 중복 확인")
                .setMessage("사용가능한 닉네임입니다. ")
                .setPositiveButton("확인",
                    DialogInterface.OnClickListener { dialog, id ->
                        mContext!!.openFragment(5)
                    })
            builder.show()
        }
    }
}