package com.example.blu_e.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blu_e.R
import com.example.blu_e.databinding.ActivityMenteeSignUpSuccessBinding

class MenteeSignUpSuccessActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMenteeSignUpSuccessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMenteeSignUpSuccessBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.loginPageBtn.setOnClickListener {
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}