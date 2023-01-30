package com.example.blu_e

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.databinding.ActivityFindIdBinding
import com.example.blu_e.databinding.ActivityMentorInfo1Binding

class MentorInfoActivity1 : AppCompatActivity() {
    lateinit var viewBinding: ActivityMentorInfo1Binding
    private val api = RetroInterface.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMentorInfo1Binding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.checkNicknameBtn.setOnClickListener {
            val nick = viewBinding.nickname.text.toString()

        }
        viewBinding.settingBtn.setOnClickListener {
            if(viewBinding.agreeCb.isChecked){
                val name = viewBinding.name.text.toString()
                val nickname = viewBinding.nickname.text.toString()
                val birth = viewBinding.birth.text.toString()
                val edu = viewBinding.education.text.toString()
                val address = viewBinding.addr.text.toString()
                val intro= viewBinding.introduceMent.text.toString()

//                api.signUp1(name, nickname, birth, edu, address, intro)

            }
            else{
                Log.d("checkbox", "fail")
            }
        }
    }
}