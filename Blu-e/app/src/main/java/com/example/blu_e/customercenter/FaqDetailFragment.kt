package com.example.blu_e.customercenter

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.blu_e.MainActivity
import com.example.blu_e.data.customercenter.FaqData
import com.example.blu_e.databinding.FragmentFaqDetailBinding

class FaqDetailFragment : Fragment() {
    private lateinit var mContext: MainActivity
    private var centerFragment: CenterFragment = CenterFragment()
    private lateinit var viewBinding: FragmentFaqDetailBinding
    private lateinit var question: FaqData
//    private val api = RetroInterface.create()

    companion object {
        fun newInstance(faqList: ArrayList<FaqData>, faqId: Int) = FaqDetailFragment().apply {
            arguments = Bundle().apply {
                putSerializable("faqList", faqList)
                putInt("questionId", faqId)
            }
        }
    }
    val receivedFaqList by lazy { requireArguments().getSerializable("faqList") as ArrayList<FaqData>}
    val receviedQuestionId by lazy { requireArguments().getInt("questionId")}

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentFaqDetailBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewBinding.backToCenterDF.setOnClickListener{
            mContext!!.openFragment(2)
        }

        viewBinding.btnAdd3F.setOnClickListener {
            mContext!!.openFragment(3)
        }
    }

    override fun onResume() {
        super.onResume()

        Log.d("질문", receviedQuestionId.toString())
        question = receivedFaqList[receviedQuestionId]
        viewBinding.showTitleF.setText(question.title)
        viewBinding.showAnswerF.setText(question.answer)
    }
}