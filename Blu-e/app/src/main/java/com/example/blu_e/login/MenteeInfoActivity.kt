package com.example.blu_e.login

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.blu_e.LoginResponse
import com.example.blu_e.R
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.databinding.ActivityMenteeInfoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MenteeInfoActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMenteeInfoBinding
//    private val api = RetroInterface.create()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMenteeInfoBinding.inflate(layoutInflater)
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
        viewBinding.signUpBtn.setOnClickListener {
            //비번과 비번확인이 같은지 확인
            if(viewBinding.userPw.text.toString() == viewBinding.checkPw.text.toString()) {
                //체크했는지 안했는지 확인
                if (viewBinding.agreeCb.isChecked) {
                    //Log.d("체크", "성공")
//                    val id = viewBinding.userId.text.toString()
//                    val password = viewBinding.userPw.text.toString()
//                    //본인인증에서 번호 가져옴
//                    val phone = "0101"
//                    val name = viewBinding.name.text.toString()
//                    val nickname = viewBinding.nickname.toString()
//                    //string ->LocalDate로 바꿔야함
//                    val birthStr = viewBinding.birth.text.toString()
//                    val birth = LocalDate.parse(birthStr, DateTimeFormatter.ISO_DATE)
//                    val education = viewBinding.education.text.toString()
//                    val grade = viewBinding.year.text.toString().toInt()
//                    val address = viewBinding.addr.text.toString()
//                    val introduce = viewBinding.introduceMent.text.toString()
//                    val role = 2
//                    val createAt = LocalDate.now()
//                    val updateAt = LocalDate.now()
//                    val status = 1
//                    //val userImg = viewBinding.
//                    api.signup(id, password,phone, name,nickname,birth,education,null, grade,address, introduce,role,createAt,updateAt,status, null)
//                        .enqueue(object : Callback<LoginResponse> {
//                            override fun onResponse(
//                                call: Call<LoginResponse>,
//                                response: Response<LoginResponse>
//                            ) {
//                                val responseData = response.body()
//                                if (responseData != null) {
//                                    if(responseData.code == 1000){
//
//                                    } else{
//                                        val msg = when(responseData.code) {
//                                            2001 -> "아이디를 입력해주세요"
//                                            2002 -> "아이드는 35자리로 이하로 입력해주세요."
//                                            2003 -> "비밀번호를 입력하세요"
//                                            else -> {}
//                                        }
//                                    }
//                                }
//                            }
//
//                            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                                TODO("Not yet implemented")
//                            }
//                        })

                }
            }
            //비번과 비번확인이 같지 않으면
            else{

            }
        }
    }
}