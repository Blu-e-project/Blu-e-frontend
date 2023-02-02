package com.example.blu_e.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.databinding.ActivityMentorInfo1Binding

class MentorInfoActivity1 : AppCompatActivity() {
    lateinit var viewBinding: ActivityMentorInfo1Binding
    private val api = RetroInterface.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMentorInfo1Binding.inflate(layoutInflater)
        setContentView(viewBinding.root)


        //뒤로가기(멘토 회원가입 페이지로)
        viewBinding.backToCenterD.setOnClickListener {
            var intent = Intent(this, MentorSignUpActivity::class.java)
            startActivity(intent)
        }
        //닉네임 중복확인
        viewBinding.checkNicknameBtn.setOnClickListener {
            val nick = viewBinding.nickname.text.toString()

        }
        viewBinding.settingBtn.setOnClickListener {
        
            //다음으로 넘어가기 위한 조건 (아이디 비번 설정 페이지로
        if(viewBinding.agreeCb.isChecked){
              //  val name = viewBinding.name.text.toString()
                //val nickname = viewBinding.nickname.text.toString()
                //val birth = viewBinding.birth.text.toString()
                //val edu = viewBinding.education.text.toString()
                //val address = viewBinding.addr.text.toString()
                //val intro= viewBinding.introduceMent.text.toString()
//                api.signUp1(name, nickname, birth, edu, address, intro)

            //}
            //else{
              //  Log.d("checkbox", "fail")
            var intent = Intent(this, MentorInfoActivity2::class.java)
            startActivity(intent)
            }
        }
    }
}