package com.example.blu_e.mainPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.blu_e.R
import com.example.blu_e.databinding.FragmentHomeNewMenteeBinding

class HomeNewMenteeFragment : Fragment() {
    private lateinit var viewBinding: FragmentHomeNewMenteeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentHomeNewMenteeBinding.inflate(layoutInflater)

        return viewBinding.root
    }
}