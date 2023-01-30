package com.example.blu_e

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.data.User
import com.example.blu_e.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import retrofit2.Retrofit

class LoginActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityLoginBinding
    private val api = RetroInterface.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        
        //로그인 기능만 구현함 실패시 ui 수정 필요
        viewBinding.loginBtn.setOnClickListener {
            val id = viewBinding.userId.text.toString()
            val pw = viewBinding.userPw.text.toString()

//            Log.d("아이디", "아이디는 ${id}")
//            Log.d("비번", "비번은 ${pw}")
            api.login(id,pw).enqueue(object :Callback<User>{
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    Log.d("login", "성공")
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                   Log.e("login","error", t)
                }
            })

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