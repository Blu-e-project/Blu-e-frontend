package com.example.blu_e

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.blu_e.databinding.FragmentGroupBinding
import com.example.blu_e.databinding.FragmentQuestionFormBinding

class QuestionFormFragment : Fragment() {
    private lateinit var mContext: MainActivity
    private val api = RetroInterface.create()
    private lateinit var viewBinding: FragmentQuestionFormBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentQuestionFormBinding.inflate(inflater, container, false)
        return viewBinding.root

        viewBinding.backToCenter.setOnClickListener{
            mContext!!.openFragment(2)
        }

        viewBinding.btnSave.setOnClickListener {
            val builder = AlertDialog.Builder(mContext)
            builder
                .setTitle("Q&amp;A 등록")
                .setMessage("Q&amp;A 등록이 완료 되었습니다.")
                .setPositiveButton("확인",
                    DialogInterface.OnClickListener { dialog, id ->
                        val bundle = Bundle()
                        api.questionWriting()
//                        bundle.putInt("questionId", question.questionId)
//                        bundle.putString("content", question.contents)

                        mContext!!.openFragment(2)
                    })
                .setNegativeButton("취소",
                    DialogInterface.OnClickListener { dialog, id ->

                    })
            builder.show()
        }
    }
}