package com.example.blu_e

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.blu_e.databinding.FragmentCenterBinding

class CenterFragment : Fragment() {
    private lateinit var viewBinding: FragmentCenterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentCenterBinding.inflate(inflater, container, false)

        return viewBinding.root
    }
}