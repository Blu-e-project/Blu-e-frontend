package com.example.blu_e.mainPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blu_e.R
import com.example.blu_e.databinding.ActivityHomeMentorBinding

class HomeMentorActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityHomeMentorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_mentor)
    }
}