package com.example.blu_e.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blu_e.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //멘토로 가입하기 페이지로
        viewBinding.mentorBtn.setOnClickListener {
            val mintent = Intent(this, MentorSignUpActivity::class.java)
            startActivity(mintent)
        }
        //멘티로 가입하기 페이지로
        viewBinding.menteeBtn.setOnClickListener {
            val intent = Intent(this, MenteeSignUpActivity::class.java)
            startActivity(intent)
        }
    }
}