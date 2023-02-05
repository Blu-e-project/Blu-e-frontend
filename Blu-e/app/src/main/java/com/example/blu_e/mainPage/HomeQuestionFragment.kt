package com.example.blu_e.mainPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.blu_e.data.MenteeData
import com.example.blu_e.data.QuestionData
import com.example.blu_e.databinding.FragmentHomeNewMentorBinding
import com.example.blu_e.databinding.FragmentHomeQuestionBinding
import com.example.blu_e.databinding.FragmentHomeRecruitMenteeBinding

class HomeQuestionFragment : Fragment() {
    private lateinit var viewBinding: FragmentHomeQuestionBinding

    companion object { //객체 생성
        fun newInstance(questionList: ArrayList<QuestionData>, questionId: Int) = HomeQuestionFragment().apply {
            arguments = Bundle().apply {
                putSerializable("questionList", questionList)
                putInt("questionId", questionId)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentHomeQuestionBinding.inflate(layoutInflater)

        return viewBinding.root
    }
}