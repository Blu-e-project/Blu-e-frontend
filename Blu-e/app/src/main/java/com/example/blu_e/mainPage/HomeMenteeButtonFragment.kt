package com.example.blu_e.mainPage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.blu_e.MainActivity
import com.example.blu_e.R
import com.example.blu_e.databinding.FragmentHomeMenteeButtonBinding
import com.example.blu_e.databinding.FragmentHomeMentorBinding

class HomeMenteeButtonFragment : Fragment() {
    private lateinit var mContext: MainActivity
    private lateinit var viewBinding: FragmentHomeMenteeButtonBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentHomeMenteeButtonBinding.inflate(layoutInflater)

        return viewBinding.root
    }
}