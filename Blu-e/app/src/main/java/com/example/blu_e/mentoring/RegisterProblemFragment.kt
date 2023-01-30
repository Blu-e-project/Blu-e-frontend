package com.example.blu_e.mentoring

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.blu_e.databinding.FragmentRegisterProblemBinding

class RegisterProblemFragment : Fragment() {
    private lateinit var viewBinding: FragmentRegisterProblemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentRegisterProblemBinding.inflate(inflater, container, false)
        return viewBinding.root
    }
}