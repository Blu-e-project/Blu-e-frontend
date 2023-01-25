package com.example.blu_e

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Callback
import com.example.blu_e.databinding.FragmentCenterBinding
import retrofit2.Call
import retrofit2.Response

class CenterFragment : Fragment() {
    private lateinit var mContext: MainActivity
    private lateinit var viewBinding: FragmentCenterBinding
    private val api = RetroInterface.create()
    private lateinit var faqs: ArrayList<Question>
    private lateinit var qs: ArrayList<Question>
    private lateinit var adapter: FaqAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentCenterBinding.inflate(inflater, container, false)
        return viewBinding.root
        adapter.setItemClickListener(object : FaqAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                val fragment = QuestionFormFragment()
                val bundle = Bundle()
                bundle.putInt("questionId", faqs[position].questionId)
                fragment.arguments = bundle
                mContext?.openFragment(1)
            }
        })

        viewBinding.btnAdd.setOnClickListener {
            mContext!!.openFragment(3)
        }
    }

    override fun onResume() {
        super.onResume()

        //자주하는 질문과 내가 작성한 글 -> 한 화면에 담는 것 고민 필요.
        api.allFAQ().enqueue(object: Callback <ArrayList<Question>>{
            override fun onResponse(call: Call<ArrayList<Question>>, response: Response<ArrayList<Question>>) {
                //성공시
                faqs = response.body() ?: return
                adapter = FaqAdapter(faqs)
                viewBinding.recyclerViewFaq.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<Question>>, t: Throwable) {
                //실패시
            }
        })

        api.requestMyQuestions().enqueue(object: Callback <ArrayList<Question>>{
            override fun onResponse(call: Call<ArrayList<Question>>, response: Response<ArrayList<Question>>) {
                //성공시
                qs = response.body() ?: return
                adapter = FaqAdapter(qs)
                viewBinding.recyclerViewQa.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<Question>>, t: Throwable) {
                //실패시
            }
        })


    }
}