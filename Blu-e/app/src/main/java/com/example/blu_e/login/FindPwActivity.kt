package com.example.blu_e.login

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.blu_e.FindIdResponse
import com.example.blu_e.SignupResponse
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.databinding.ActivityFindPwBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FindPwActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityFindPwBinding
    private val api = RetroInterface.create()
    lateinit var phoneNum : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityFindPwBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        //뒤로가기(로그인으로 돌아가기)
        viewBinding.backToCenterD.setOnClickListener {
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        //인증코드 발송
        var phoneNumSuccess = false
        var verifySuccess = false
        viewBinding.smsBtn.setOnClickListener {
            phoneNum = viewBinding.phoneNumber.text.toString()
            api.sendPhoneNum(phoneNum).enqueue(object : Callback<SignupResponse> {
                override fun onResponse(
                    call: Call<SignupResponse>,
                    response: Response<SignupResponse>
                ) {
                    val responseData = response.body()
                        if (responseData != null) {
                            if (responseData.code == 1000) {
                                //인증번호 전송 성공
                                phoneNumSuccess = true
                            } else if (responseData.code == 2019) {
                                //인증 문자 발신 실패
                            }
                        }
                }

                override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })

        }

        //비밀번호 재설정을 위한 아이디 비밀번호 입력페이지로 이동
        viewBinding.resetPwBtn.setOnClickListener {
            if(phoneNumSuccess){
                val verifyEncode = viewBinding.verifyNum.text.toString()
                api.verifyCode(phoneNum, verifyEncode).enqueue(object :Callback<SignupResponse> {
                    override fun onResponse(
                        call: Call<SignupResponse>,
                        response: Response<SignupResponse>
                    ) {
                        val responseData = response.body()
                        if (responseData != null) {
                            if (responseData.code == 1000) {
                                //인증 성공
                                var intent =
                                    Intent(this@FindPwActivity, ResetPwActivity::class.java)
                                intent.putExtra("phoneNum", phoneNum)
                                startActivity(intent)
                            } else if (responseData.code == 2020) {
                                //인증 실패
                            }
                        }
                    }

                    override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                })
            }
        }
    }
}