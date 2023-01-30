package com.example.blu_e

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blu_e.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}