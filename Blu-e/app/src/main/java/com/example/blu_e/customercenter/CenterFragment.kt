package com.example.blu_e.customercenter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blu_e.MainActivity
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.data.customercenter.*
import com.example.blu_e.databinding.FragmentCenterBinding
import com.example.blu_e.mentoring.AskQuestionActivity
import com.example.blu_e.mentoring.RequestMentoringActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CenterFragment : Fragment() {
    private lateinit var mContext: MainActivity
    private lateinit var viewBinding: FragmentCenterBinding
    private lateinit var faqExampleList: ArrayList<FaqData>
    private lateinit var qs: ArrayList<Question>
    private lateinit var adapter: FaqAdapter
    private lateinit var adapter2: QuestionAdapter
    private val api = RetroInterface.create()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentCenterBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        faqExampleList = ArrayList<FaqData>()
        var questionExample: FaqData
        for (i in 0..5) {
            questionExample = FaqData(i)
            faqExampleList.add(i, questionExample)
        }
        faqExampleList.get(0).title = "Q&A를 남기고 싶어요."
        faqExampleList.get(0).answer = "자주하는 질문1에 대한 답변"
        faqExampleList.get(1).title = "멘토/멘티 신고는 어떻게 하나요?"
        faqExampleList.get(1).answer = "자주하는 질문2에 대한 답변"
        faqExampleList.get(2).title = "모든 서비스가 무료인가요?"
        faqExampleList.get(2).answer = "자주하는 질문3에 대한 답변"
        faqExampleList.get(3).title = "멘토는 같은 지역에 멘티만 구할 수 있나요?"
        faqExampleList.get(3).answer = "자주하는 질문4에 대한 답변"
        faqExampleList.get(4).title = "멘티는 몇 개 과목까지 멘토를 구할 수 있나요?"
        faqExampleList.get(4).answer = "자주하는 질문5에 대한 답변"
        faqExampleList.get(5).title = "활동을 중간에 중단하면 어떤 불이익이 있나요?"
        faqExampleList.get(5).answer = "자주하는 질문6에 대한 답변"

        adapter = FaqAdapter(faqExampleList)
        viewBinding.recyclerViewFaq.adapter = adapter
        viewBinding.recyclerViewFaq.layoutManager = LinearLayoutManager(mContext)

        adapter.setItemClickListener(object : FaqAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                var detailFragment = FaqDetailFragment.newInstance(faqExampleList, position)
                mContext.supportFragmentManager.beginTransaction().replace(
                    mContext.viewBinding.containerFragment.id, detailFragment
                ).commit()
            }
        })
        viewBinding.exampleBtn.setOnClickListener {
            var intent = Intent(getActivity(), RequestMentoringActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        viewBinding.btnAdd.setOnClickListener {
            mContext!!.openFragment(3)
        }

        api.requestMyQuestions().enqueue(object: Callback<QuestionResponse> {
             override fun onResponse(call: Call<QuestionResponse>, response: Response<QuestionResponse>) {
                 val body = response.body()?: return
                 if (body != null) {
                     if(body.code == 1000) {
                         if(body.result != null) {
                             Log.d("질문 목록 불러오기", "성공")
                             qs = body.result
                             adapter2 = QuestionAdapter(qs)
                             viewBinding.recyclerViewQa.adapter = adapter2
                             viewBinding.recyclerViewQa.layoutManager = LinearLayoutManager(mContext)
                             adapter2.notifyItemChanged(qs.size)

                             adapter2.setItemClickListener(object: QuestionAdapter.ItemClickListener{
                                 override fun onClick(view: View, position: Int) {
                                     var detailFragment = QuestionDetailFragment.newInstance(qs, position)
                                     mContext.supportFragmentManager.beginTransaction().replace(
                                         mContext.viewBinding.containerFragment.id, detailFragment
                                     ).commit()
                                 }
                             })
                         } else {
                             Log.d("질문 목록 불러오기", "아직 질문이 없습니다.")
                         }
                     }
                 }
                 else {
                     Log.d("Response: ", "null")
                 }
             }
             override fun onFailure(call: Call<QuestionResponse>, t: Throwable) {
                 //실패시
                 Log.d("질문 목록 불러오기", "실패")
             }
         })
    }
}