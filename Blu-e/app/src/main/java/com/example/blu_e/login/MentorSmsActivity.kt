package com.example.blu_e.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.blu_e.LoginResponse
import com.example.blu_e.MainApplication
import com.example.blu_e.R
import com.example.blu_e.SignupResponse
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.databinding.ActivityMentorSmsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MentorSmsActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMentorSmsBinding
    private val api = RetroInterface.create()
    lateinit var phoneNum: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMentorSmsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //인증번호 요청
        var phoneNumSuccess = false

        viewBinding.smsBtn.setOnClickListener {
            phoneNum = viewBinding.phoneNumber.text.toString()
            Log.d("번호", "${phoneNum}")
            api.sendPhoneNum(phoneNum).enqueue(object : Callback<SignupResponse>{
                override fun onResponse(
                    call: Call<SignupResponse>,
                    response: Response<SignupResponse>
                ) {
                    val responseData = response.body()
                    if (responseData != null) {
                        if(responseData.code == 1000){
                            //전화번호 인증 성공
                            phoneNumSuccess = true

                        }
                        else if(responseData.code == 2019){
                            //인증 문자 발신 실패
                        }
                    }
                }

                override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                    //인증 문자 발송 실패
                }

            })
        }

        viewBinding.mentorInfoBtn.setOnClickListener {
            if(phoneNumSuccess){
                val verifyCode = viewBinding.verifyNum.text.toString()
                api.verifyCode(phoneNum, verifyCode).enqueue(object: Callback<SignupResponse>{
                    override fun onResponse(
                        call: Call<SignupResponse>,
                        response: Response<SignupResponse>
                    ) {
                        val responseData = response.body()
                        if (responseData != null) {
                            if(responseData.code == 1000){
                                //성공
                                val mintent = Intent(this@MentorSmsActivity, MentorInfoActivity::class.java)
                                mintent.putExtra("mentorPhoneNum", phoneNum)
                                Log.d("전번", "${phoneNum}")
                                startActivity(mintent)
                            }
                            else if(responseData.code == 2019){
                                //인증 문자 발송에 실패했습니다. 다이얼로그
                            }
                        }
                    }

                    override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })
            }
            else{
                //번호 다시 입력해주세요!
            }
        }

    }
}