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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blu_e.MainActivity
import com.example.blu_e.data.Answer
import com.example.blu_e.data.FaqAdapter
import com.example.blu_e.data.Question
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.databinding.FragmentQuestionDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.sql.Timestamp

class QuestionDetailFragment : Fragment() {
    private lateinit var mContext: MainActivity
    private lateinit var viewBinding: FragmentQuestionDetailBinding
    private lateinit var question: Question
    private val api = RetroInterface.create()

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
            val builder = AlertDialog.Builder(mContext)
            builder
                .setTitle("Q&A 삭제")
                .setMessage("질문을 삭제하시겠습니까?")
                .setPositiveButton("예",
                    DialogInterface.OnClickListener { dialog, id ->
                        //userId와 questionId
                        api.questionDelete(0, 0)
                    })
                .setNegativeButton("아니오",
                    DialogInterface.OnClickListener { dialog, id ->
                    })
            builder.show()
        }
    }

    override fun onResume() {
        super.onResume()
        var questionId = requireArguments().getInt("questionId")

        Log.d("질문", questionId.toString())

        question = Question(questionId)
        viewBinding.showTitle.setText(question.title)
        viewBinding.showContent.setText(question.contents)

        api.requestAnswerInQuestion(0).enqueue(object: Callback<Answer> {
            override fun onResponse(call: Call<Answer>, response: Response<Answer>) {
                //성공시
                if(response.isSuccessful) {
                    val answer = response.body()
//                    viewBinding.showAnswer.setText() //Async ..
                }
            }

            override fun onFailure(call: Call<Answer>, t: Throwable) {
                //실패시
            }
        })
    }
}