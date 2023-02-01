package com.example.blu_e

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blu_e.databinding.ActivityMenteeInfo2Binding

class MenteeInfoActivity2 : AppCompatActivity() {
    lateinit var viewBinding: ActivityMenteeInfo2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding =  ActivityMenteeInfo2Binding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.signUpBtn.setOnClickListener {
            var intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}