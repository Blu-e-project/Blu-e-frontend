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
import com.example.blu_e.databinding.FragmentMentorChangePasswdBinding

class MentorPasswdChangeFragment:Fragment() {
    private lateinit var mContext: MainActivity
    private lateinit var viewBinding: FragmentMentorChangePasswdBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as MainActivity
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMentorChangePasswdBinding.inflate(inflater, container, false)
        return viewBinding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewBinding.backToPageMentor.setOnClickListener{
            mContext!!.openFragment(5)
        }

        viewBinding.btnChangePwd.setOnClickListener {
            val builder = AlertDialog.Builder(mContext)
            builder
                .setTitle("비밀번호 변경")
                .setMessage("비밀번호 변경이 완료되었습니다. ")
                .setPositiveButton("확인",
                    DialogInterface.OnClickListener { dialog, id ->
                        mContext!!.openFragment(5)
                    })
            builder.show()
        }
    }

}