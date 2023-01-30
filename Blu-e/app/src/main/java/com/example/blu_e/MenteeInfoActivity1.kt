package com.example.blu_e

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blu_e.databinding.ActivityMenteeInfo1Binding

class MenteeInfoActivity1 : AppCompatActivity() {
    lateinit var viewBinding: ActivityMenteeInfo1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_mentee_info1)
    }
}