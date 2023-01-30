package com.example.blu_e

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.blu_e.databinding.FragmentRecruitMentorBinding

class RecruitMentorFragment: Fragment() {
    private lateinit var viewBinding: FragmentRecruitMentorBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentRecruitMentorBinding.inflate(inflater, container, false)

        return viewBinding.root
    }
}