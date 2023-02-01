package com.example.blu_e.mainPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.blu_e.R
import com.example.blu_e.databinding.FragmentHomeNewMenteeBinding
import com.example.blu_e.databinding.FragmentHomeNewMentorBinding

class HomeNewMentorFragment : Fragment() {

    private lateinit var viewBinding: FragmentHomeNewMentorBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentHomeNewMentorBinding.inflate(layoutInflater)

        return viewBinding.root
    }
}