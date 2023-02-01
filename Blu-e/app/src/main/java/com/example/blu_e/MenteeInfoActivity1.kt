package com.example.blu_e

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blu_e.databinding.ActivityMenteeInfo1Binding

class MenteeInfoActivity1 : AppCompatActivity() {
    lateinit var viewBinding: ActivityMenteeInfo1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(viewBinding.root)

        //뒤로가기(멘티 회원가입 페이지로)
        viewBinding.backToCenterD.setOnClickListener {
            var intent = Intent(this, MenteeSignUpActivity::class.java)
            startActivity(intent)
        }
        //닉네임 중복확인
        viewBinding.checkNicknameBtn.setOnClickListener {
            val nick = viewBinding.nickname.text.toString()

        }
        viewBinding.settingBtn.setOnClickListener {

            //다음으로 넘어가기 위한 조건 (멘티 아이디 비번 설정 페이지로)
            if (viewBinding.agreeCb.isChecked) {
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
                var intent = Intent(this, MenteeInfoActivity2::class.java)
                startActivity(intent)
            }
        }
    }
}