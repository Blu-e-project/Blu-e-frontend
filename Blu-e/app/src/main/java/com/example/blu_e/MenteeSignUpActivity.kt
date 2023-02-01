package com.example.blu_e

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blu_e.databinding.ActivityMenteeSignUpBinding

class MenteeSignUpActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMenteeSignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMenteeSignUpBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //뒤로가기(회원가입 페이지로)
        viewBinding.backToCenterD.setOnClickListener {
            var intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        //멘티 정보입력으로 이동
        viewBinding.menteeBtn.setOnClickListener {
            var intent = Intent(this, MenteeInfoActivity1::class.java)
            startActivity(intent)
        }
    }
}