package com.example.blu_e.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blu_e.databinding.ActivityMentorSignUpBinding

class MentorSignUpActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMentorSignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMentorSignUpBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //뒤로가기(회원가입 페이지로)
        viewBinding.backToCenterD.setOnClickListener {
            var intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        //멘티 정보입력으로 이동
        viewBinding.mentorBtn.setOnClickListener {
            var intent = Intent(this, MentorSmsActivity::class.java)
            startActivity(intent)
        }
    }
}