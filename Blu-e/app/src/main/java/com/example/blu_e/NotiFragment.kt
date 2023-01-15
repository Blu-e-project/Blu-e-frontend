package com.example.blu_e

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.blu_e.databinding.FragmentNotiBinding

class NotiFragment : Fragment() {
    private lateinit var viewBinding: FragmentNotiBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentNotiBinding.inflate(inflater, container, false)
        return viewBinding.root
    }
}