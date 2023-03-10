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
import com.example.blu_e.MainApplication
import com.example.blu_e.data.ResponseData
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
    private val api = RetroInterface.create()

    companion object {
        fun newInstance(faqList: ArrayList<Question>, position: Int) = QuestionDetailFragment().apply {
            arguments = Bundle().apply {
                putSerializable("list", faqList)
                putInt("position", position)
            }
        }
    }
    val receivedList by lazy { requireArguments().getSerializable("list") as ArrayList<Question>}
    val receivedPosition by lazy { requireArguments().getInt("position")}

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
            var questionId: Int = receivedList.get(receivedPosition).questionId
            Log.d("?????? id", receivedList.get(receivedPosition).questionId.toString())
            val builder = AlertDialog.Builder(mContext)
            builder
                .setTitle("Q&A ??????")
                .setMessage("????????? ?????????????????????????")
                .setPositiveButton("???",
                    DialogInterface.OnClickListener { dialog, id ->
                        api.questionDelete(questionId).enqueue(object: Callback<ResponseData> {
                            override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                                val body = response.body() ?: return
                                if(body != null) {
                                    if(body.code == 1000) {
                                        Log.d("?????? ????????????", body.message)
                                        Log.d("questionId........!!!!", questionId.toString())
                                        mContext!!.openFragment(2)
                                    }
                                    else {
                                        Log.d("?????? ????????????", "??????")
                                    }
                                } else {
                                    Log.d("?????? ????????????(????????????)", "??????")
                                }
                            }
                            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                                Log.d("?????? ????????????", "??????")
                            }
                        })
                    })
                .setNegativeButton("?????????",
                    DialogInterface.OnClickListener { dialog, id ->
                    })
            builder.show()
        }
    }

    override fun onResume() {
        super.onResume()

        question = receivedList[receivedPosition]
        viewBinding.showTitle.text = question.title
        viewBinding.showContent.text = question.contents
        viewBinding.showAnswer.text = question.answer
    }
}