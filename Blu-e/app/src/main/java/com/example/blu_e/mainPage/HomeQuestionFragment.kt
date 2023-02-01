package com.example.blu_e.mainPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.blu_e.databinding.FragmentHomeNewMentorBinding
import com.example.blu_e.databinding.FragmentHomeQuestionBinding
import com.example.blu_e.databinding.FragmentHomeRecruitMenteeBinding

class HomeQuestionFragment : Fragment() {
    private lateinit var viewBinding: FragmentHomeQuestionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentHomeQuestionBinding.inflate(layoutInflater)

        return viewBinding.root
    }
}