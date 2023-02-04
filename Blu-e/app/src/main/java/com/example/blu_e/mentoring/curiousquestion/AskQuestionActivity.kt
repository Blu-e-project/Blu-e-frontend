package com.example.blu_e.mentoring.curiousquestion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blu_e.databinding.ActivityRequestMentoringBinding

class AskQuestionActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityRequestMentoringBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityRequestMentoringBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}