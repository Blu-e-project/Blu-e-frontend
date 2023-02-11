package com.example.blu_e.login

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.blu_e.LoginResponse
import com.example.blu_e.MainActivity
import com.example.blu_e.MainApplication
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.data.mainPage.FindMenteeIdResponse
import com.example.blu_e.data.mainPage.FindMentorIdResponse
import com.example.blu_e.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityLoginBinding
    private val api = RetroInterface.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        var jwt: String?

        //로그인 기능만 구현함 실패시 ui 수정 필요
        viewBinding.loginBtn.setOnClickListener {
            val id = viewBinding.userId.text.toString()
            val pw = viewBinding.userPw.text.toString()

            Log.d("login", "아이디는 ${id}")
            Log.d("login", "비번은 ${pw}")

            val intent = Intent(this, MainActivity::class.java)
            api.login(id,pw).enqueue(object : Callback<LoginResponse> {

                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    val responseData = response.body()
                    //성공하면
                    if (responseData != null) {
                        if(responseData.code == 1000) {
                            Log.d("login", "성공")
                            jwt = responseData.result.jwt
                            Log.d("login", "${jwt}")
                            MainApplication.prefs.setString("blu-e-access-token", jwt!!)
                            val userId = responseData.result.userId
                            MainApplication.prefs.setString("userId", userId.toString())
                            //멘티에 아이디가 있으면 꺼냄
                            api.findMenteeID(userId).enqueue(object: Callback<FindMenteeIdResponse>{
                                override fun onResponse(
                                    call: Call<FindMenteeIdResponse>,
                                    response: Response<FindMenteeIdResponse>
                                ) {
                                    //멘티 user정보에 user가 있으면?
                                    val menteeResponseData = response.body()
                                    if (menteeResponseData != null) {
                                        if(menteeResponseData.code == 1000){
                                            MainApplication.prefs.setString("role", "2")
                                        }
                                        else{
                                            MainApplication.prefs.setString("role", "1")
                                        }
                                    }
                                }

                                override fun onFailure(
                                    call: Call<FindMenteeIdResponse>,
                                    t: Throwable
                                ) {

                                }

                            })
                            Log.d("사용자 역할 정보", "${MainApplication.prefs.getString("role","")}")
                            startActivity(intent)
                        }
                        //비밀번호를 입력해주세요x
                        if (responseData.code == 2017){
                            viewBinding.pwMsg.text = responseData.message
                            viewBinding.userPw.backgroundTintList = ColorStateList.valueOf(Color.rgb(255,0,0))
                        }
                        if (responseData.code == 3002){
                            viewBinding.idMsg.text = "아이디가 존재하지 않습니다."
                            viewBinding.userId.backgroundTintList = ColorStateList.valueOf(Color.rgb(255,0,0))
                        }
                        if (responseData.code == 3003){
                            viewBinding.idMsg.text = "일치하는 회원 정보가 없습니다"
                            viewBinding.userPw.backgroundTintList = ColorStateList.valueOf(Color.rgb(255,0,0))
                        }
                        if (responseData.code == 3004){
                            viewBinding.idMsg.text = "비활성화 된 계정입니다. 고객센터에 문의해주세요."
                            viewBinding.userPw.backgroundTintList = ColorStateList.valueOf(Color.rgb(255,0,0))
                        }
                    }
                    //에러 메세지 1초만 띄우기
                    Handler().postDelayed(Runnable {
                        viewBinding.pwMsg.text = ""
                        viewBinding.idMsg.text = ""
                        viewBinding.userId.backgroundTintList = ColorStateList.valueOf(Color.rgb(0,107,206))
                        viewBinding.userPw.backgroundTintList = ColorStateList.valueOf(Color.rgb(0,107,206))
                    }, 1000)
                }


                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.e("error", t.toString())
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