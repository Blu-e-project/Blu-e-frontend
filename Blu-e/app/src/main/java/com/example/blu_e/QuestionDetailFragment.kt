package com.example.blu_e

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.blu_e.databinding.FragmentQuestionDetailBinding

class QuestionDetailFragment : Fragment() {
    private lateinit var mContext: MainActivity
    private lateinit var viewBinding: FragmentQuestionDetailBinding
    private lateinit var question: Question

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentQuestionDetailBinding.inflate(inflater, container, false)

        return viewBinding.root

        var questionId = requireArguments().getInt("questionId")

        Log.d("질문", questionId.toString())

        question = Question(questionId)
        viewBinding.showTitle.setText(question.title)
        viewBinding.showContent.setText(question.contents)
        viewBinding.showAnswer.setText(question.answer.contents)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewBinding.backToCenterD.setOnClickListener{
            mContext!!.openFragment(2)
        }

        viewBinding.btnAdd3.setOnClickListener {
            mContext!!.openFragment(3)
        }
    }
}