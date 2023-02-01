package com.example.blu_e

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.blu_e.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        var handler = Handler()
        handler.postDelayed({
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }, 1000)

    }

    override fun onPause() {
        //대기 상태일 때 종료
        super.onPause()
        finish()
    }
}