package com.example.blu_e

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blu_e.databinding.ActivityMentorInfo2Binding

class MentorInfoActivity2 : AppCompatActivity() {
    lateinit var viewBinding: ActivityMentorInfo2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMentorInfo2Binding.inflate(layoutInflater)
        setContentView(R.layout.activity_mentor_info2)
        setContentView(viewBinding.root)

        //뒤로가기(멘토 정보입력 1)
        viewBinding.backToCenterD.setOnClickListener {
            var intent = Intent(this, MentorInfoActivity1::class.java)
            startActivity(intent)
        }
        
        // 가입 성공 페이지로 이동
        viewBinding.signUpBtn.setOnClickListener {
            var intent = Intent(this, MentorSignUpSuccessActivity::class.java)
            startActivity(intent)
        }

    }
}