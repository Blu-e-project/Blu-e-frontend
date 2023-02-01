package com.example.blu_e

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blu_e.databinding.ActivityFindIdBinding
import com.example.blu_e.databinding.ActivityFindPwBinding

class FindPwActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityFindPwBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityFindPwBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //뒤로가기(로그인으로 돌아가기)
        viewBinding.backToCenterD.setOnClickListener {
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}