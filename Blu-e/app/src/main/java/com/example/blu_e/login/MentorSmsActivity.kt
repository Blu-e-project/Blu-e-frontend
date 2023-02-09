package com.example.blu_e.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blu_e.LoginResponse
import com.example.blu_e.R
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.databinding.ActivityMentorSmsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MentorSmsActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMentorSmsBinding
   // private val api = RetroInterface.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMentorSmsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

       //인증번호 요청
       var phoneNumSuccess = false
       val phoneNum = viewBinding.phoneNumber.text.toString()
       viewBinding.smsBtn.setOnClickListener {
//            api.sendPhoneNum(phoneNum).enqueue(object: Callback<LoginResponse>{
//                override fun onResponse(
//                    call: Call<LoginResponse>,
//                    response: Response<LoginResponse>
//                ) {
//                    val responseData = response.body()
//                    if (responseData != null) {
//                        if(responseData.code == 1000){
//                            //전화번호 인증 성공
//                            phoneNumSuccess = true
//                        }
//                        else if(responseData.code == 2019){
//                            //인증 문자 발신 실패
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                    TODO("Not yet implemented")
//                }
//
//            })
       }

       viewBinding.mentorInfoBtn.setOnClickListener {
//            if(phoneNumSuccess) {
//                val verifyCode = viewBinding.verifyNum.text.toString()
//                api.verifyCode(phoneNum, verifyCode).enqueue(object : Callback<LoginResponse> {
//                    override fun onResponse(
//                        call: Call<LoginResponse>,
//                        response: Response<LoginResponse>
//                    ) {
//                        val responseData = response.body()
//                        if (responseData != null) {
//                            if(responseData.code == 1000){
//                                //성공

           var intent = Intent(this, MentorInfoActivity::class.java)
           intent.putExtra("phoneNum", phoneNum)
           startActivity(intent)
//                            }
//                            else if(responseData.code == 2020){
//                                //휴대폰 인증에 실패
//                            }
//                        }
//                    }
//
//                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                        TODO("Not yet implemented")
//                    }
//
//                })
//            }
//            //휴대폰 번호 입력이 안되었으면
//            else{
//
//            }
       }

    }
}