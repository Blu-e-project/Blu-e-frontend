package com.example.blu_e.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blu_e.MainActivity
import com.example.blu_e.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityLoginBinding
//    private val api = RetroInterface.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        
        //로그인 기능만 구현함 실패시 ui 수정 필요
        viewBinding.loginBtn.setOnClickListener {
            val id = viewBinding.userId.text.toString()
            val pw = viewBinding.userPw.text.toString()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
//            Log.d("아이디", "아이디는 ${id}")
//            Log.d("비번", "비번은 ${pw}")
//            api.login(id,pw).enqueue(object :Callback<User>{
//                override fun onResponse(call: Call<User>, response: Response<User>) {
//                    Log.d("login", "성공")
//                    startActivity(intent)
//                }
//
//                override fun onFailure(call: Call<User>, t: Throwable) {
//                   Log.e("login","error", t)
//                }
//            })

        }
//    아이디 찾기 페이지로
        viewBinding.findId.setOnClickListener {
            val intent = Intent(this, FindIdActivity:: class.java)
            startActivity(intent)
        }
        //비밀번호 찾기 페이지로
        viewBinding.findPw.setOnClickListener{
            val intent = Intent(this, FindPwActivity::class.java)
            startActivity(intent)
        }
        //회원가입 페이지로
        viewBinding.signUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}