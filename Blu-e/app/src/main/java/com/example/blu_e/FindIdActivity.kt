package com.example.blu_e

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blu_e.databinding.ActivityFindIdBinding

class FindIdActivity : AppCompatActivity() {
    lateinit var viewBinding : ActivityFindIdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityFindIdBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //뒤로가기(로그인 화면으로)
        viewBinding.backToCenterD.setOnClickListener {
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }
}