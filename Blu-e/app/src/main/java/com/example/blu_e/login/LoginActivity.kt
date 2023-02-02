package com.example.blu_e.login

import android.content.Intent
<<<<<<< HEAD
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.blu_e.LoginResponse
import com.example.blu_e.MainActivity
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
=======
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blu_e.MainActivity
import com.example.blu_e.databinding.ActivityLoginBinding
>>>>>>> c5d86b6eba49fd686c254af4536ae673faa30701


class LoginActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityLoginBinding
<<<<<<< HEAD
    private val api = RetroInterface.create()
=======
//    private val api = RetroInterface.create()
>>>>>>> c5d86b6eba49fd686c254af4536ae673faa30701
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        
        //로그인 기능만 구현함 실패시 ui 수정 필요
        viewBinding.loginBtn.setOnClickListener {
            val id = viewBinding.userId.text.toString()
            val pw = viewBinding.userPw.text.toString()

            val intent = Intent(this, MainActivity::class.java)
<<<<<<< HEAD
            Log.d("아이디", "아이디는 ${id}")
            Log.d("비번", "비번은 ${pw}")

        //            api.login(id,pw).enqueue(object : Callback<LoginResponse> {
//                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
//                    Log.d("login", "성공")
//                    val responseData = response.body()
//                    if (responseData != null) {
//                        //성공하면
//                        if(responseData.code == 1000)
//                            startActivity(intent)
//                        //비밀번호를 입력해주세요
//                        else if (responseData.code == 2017){
//                            viewBinding.pwMsg.text = responseData.message
//                            viewBinding.userPw.backgroundTintList = ColorStateList.valueOf(Color.rgb(255,0,0))
//                        }
//                        else if (responseData.code == 3002){
//                            viewBinding.idMsg.text = "아이디가 존재하지 않습니다."
//                            viewBinding.userId.backgroundTintList = ColorStateList.valueOf(Color.rgb(255,0,0))
//                        }
//                        else if (responseData.code == 3003){
//                            viewBinding.idMsg.text = "일치하는 회원 정보가 없습니다"
//                            viewBinding.userPw.backgroundTintList = ColorStateList.valueOf(Color.rgb(255,0,0))
//                        }
//                        //에러 메세지 1초만 띄우기
//            Handler().postDelayed(Runnable {
//                viewBinding.pwMsg.text = ""
//                viewBinding.idMsg.text = ""
//                viewBinding.userId.backgroundTintList = ColorStateList.valueOf(Color.rgb(0,107,206))
//                viewBinding.userPw.backgroundTintList = ColorStateList.valueOf(Color.rgb(0,107,206))
//            }, 1000)
//                    }
//                }
//
//                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                    TODO("Not yet implemented")
=======
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
>>>>>>> c5d86b6eba49fd686c254af4536ae673faa30701
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