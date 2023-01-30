package com.example.blu_e

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blu_e.databinding.ActivityFindIdBinding

class FindIdActivity : AppCompatActivity() {
    lateinit var viewBinding : ActivityFindIdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityFindIdBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


    }
}