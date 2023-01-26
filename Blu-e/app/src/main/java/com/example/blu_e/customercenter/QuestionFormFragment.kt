package com.example.blu_e.customercenter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.blu_e.MainActivity
import com.example.blu_e.data.Question
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.databinding.FragmentQuestionFormBinding
import java.sql.Timestamp

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
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewBinding.backToCenter.setOnClickListener{
            mContext!!.openFragment(2)
        }

        viewBinding.btnSave.setOnClickListener {
            val builder = AlertDialog.Builder(mContext)
            builder
                .setTitle("Q&A 등록")
                .setMessage("Q&A 등록이 완료 되었습니다.")
                .setPositiveButton("확인",
                    DialogInterface.OnClickListener { dialog, id ->
                        var question = Question(it.id).apply {
                            title = viewBinding.title.toString()
                            contents = viewBinding.content.toString()
                            createdAt = Timestamp(System.currentTimeMillis())
                        }
                        api.questionWriting(question)
                        mContext!!.openFragment(2)
                    })
                .setNegativeButton("취소",
                    DialogInterface.OnClickListener { dialog, id ->

                    })
            builder.show()
        }
    }
}