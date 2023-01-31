package com.example.blu_e.customercenter

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blu_e.MainActivity
import com.example.blu_e.data.*
import com.example.blu_e.databinding.FragmentCenterBinding

class CenterFragment : Fragment() {
    private lateinit var mContext: MainActivity
    private lateinit var viewBinding: FragmentCenterBinding
    private lateinit var faqExampleList: ArrayList<FaqData>
    private lateinit var qs: ArrayList<Question>
    private lateinit var adapter: FaqAdapter
    private lateinit var adapter2: QuestionAdapter
//    private val api = RetroInterface.create()

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
        for (i in 0..4) {
            var questionExample: FaqData = FaqData(i)
            questionExample.title = "$i 번째 자주하는 질문"
            questionExample.answer = "$i 번재 자주하는 질문의 답변"
            faqExampleList.add(i, questionExample)
        }
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

        //+ userId 받아오기
       /* api.requestMyQuestions(0).enqueue(object: Callback <ArrayList<Question>>{
            override fun onResponse(call: Call<ArrayList<Question>>, response: Response<ArrayList<Question>>) {
                //성공시
                if(response.isSuccessful) {
                    viewBinding.recyclerViewQa.layoutManager = LinearLayoutManager(mContext)
                    qs = response.body() ?: return
                    adapter2 = QuestionAdapter(qs)
                    viewBinding.recyclerViewQa.adapter = adapter2
                }
            }

            override fun onFailure(call: Call<ArrayList<Question>>, t: Throwable) {
                //실패시
            }
        })*/
    }

    override fun onResume() {
        super.onResume()

        viewBinding.btnAdd.setOnClickListener {
            mContext!!.openFragment(3)
        }
    }
}