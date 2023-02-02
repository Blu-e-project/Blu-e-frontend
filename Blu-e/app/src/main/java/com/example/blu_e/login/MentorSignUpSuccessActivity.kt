package com.example.blu_e.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blu_e.R
import com.example.blu_e.databinding.ActivityMentorSignUpSuccessBinding

class MentorSignUpSuccessActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMentorSignUpSuccessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding =ActivityMentorSignUpSuccessBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_mentor_info2)
        setContentView(viewBinding.root)

        //다시 로그인 페이지로
        viewBinding.loginPageBtn.setOnClickListener {
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}