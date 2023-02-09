package com.example.blu_e.mentoring

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blu_e.databinding.ActivityRegisterQuestionFormBinding
import com.example.blu_e.databinding.ActivityRequestMentoringBinding

class RegisterQuestionFormActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityRegisterQuestionFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityRegisterQuestionFormBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}