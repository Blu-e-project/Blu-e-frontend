package com.example.blu_e

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blu_e.databinding.ActivityFindIdBinding
import com.example.blu_e.databinding.ActivityMentorInfo1Binding

class MentorInfoActivity1 : AppCompatActivity() {
    lateinit var viewBinding: ActivityMentorInfo1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMentorInfo1Binding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.checkNicknameBtn.setOnClickListener {
            val nick = viewBinding.nickname.text.toString()

        }

    }
}