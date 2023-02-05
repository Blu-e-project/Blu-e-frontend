package com.example.blu_e.customercenter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.blu_e.MainActivity
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.data.customercenter.Question
import com.example.blu_e.data.customercenter.QuestionResponse
import com.example.blu_e.databinding.FragmentQuestionDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuestionDetailFragment : Fragment() {
    private lateinit var mContext: MainActivity
    private var centerFragment: CenterFragment = CenterFragment()
    private lateinit var viewBinding: FragmentQuestionDetailBinding
    private lateinit var question: Question
//    private val api = RetroInterface.create()

    companion object {
        fun newInstance(faqList: ArrayList<Question>, faqId: Int) = QuestionDetailFragment().apply {
            arguments = Bundle().apply {
                putSerializable("list", faqList)
                putInt("questionId", faqId)
            }
        }
    }
    val receivedFaqList by lazy { requireArguments().getSerializable("list") as ArrayList<Question>}
    val receviedQuestionId by lazy { requireArguments().getInt("questionId")}

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentQuestionDetailBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewBinding.backToCenterD.setOnClickListener{
            mContext!!.openFragment(2)
        }

        viewBinding.btnAdd3.setOnClickListener {
            mContext!!.openFragment(3)
        }

        viewBinding.questionDeleteIcon.setOnClickListener {
            val userId: Int = 1
            val builder = AlertDialog.Builder(mContext)
            builder
                .setTitle("Q&A 삭제")
                .setMessage("질문을 삭제하시겠습니까?")
                .setPositiveButton("예",
                    DialogInterface.OnClickListener { dialog, id ->
                        /*api.questionDelete("", userId, receviedQuestionId).enqueue(object: Callback<QuestionResponse> {
                            override fun onResponse(call: Call<QuestionResponse>, response: Response<QuestionResponse>) {
                                val body = response.body() ?: return
                                if(body.code == 1000) {
                                    Log.d("질문 삭제하기", "성공")
                                }
                                else {
                                    Log.d("질문 삭제하기", "실패")
                                }
                            }
                            override fun onFailure(call: Call<QuestionResponse>, t: Throwable) {
                            }
                        })      */
                    })
                .setNegativeButton("아니오",
                    DialogInterface.OnClickListener { dialog, id ->
                    })
            builder.show()
        }
    }

    override fun onResume() {
        super.onResume()

        Log.d("질문", receviedQuestionId.toString())
        question = receivedFaqList[receviedQuestionId]
        viewBinding.showTitle.text = question.title
        viewBinding.showContent.text = question.contents
    }
}