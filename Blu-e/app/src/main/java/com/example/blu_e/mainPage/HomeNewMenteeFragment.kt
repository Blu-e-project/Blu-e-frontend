package com.example.blu_e.mainPage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.blu_e.MainActivity
import com.example.blu_e.R
import com.example.blu_e.customercenter.FaqDetailFragment
import com.example.blu_e.data.FaqData
import com.example.blu_e.data.MenteeData
import com.example.blu_e.databinding.FragmentHomeNewMenteeBinding

class HomeNewMenteeFragment : Fragment() {
    private lateinit var mContext: MainActivity
    private lateinit var viewBinding: FragmentHomeNewMenteeBinding

    companion object { //객체 생성
        fun newInstance(menteeList: ArrayList<MenteeData>, menteeId: Int) = HomeNewMenteeFragment().apply {
            arguments = Bundle().apply {
                putSerializable("menteeList", menteeList)
                putInt("menteeId", menteeId)
            }
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as MainActivity
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentHomeNewMenteeBinding.inflate(layoutInflater)

        return viewBinding.root
    }
}