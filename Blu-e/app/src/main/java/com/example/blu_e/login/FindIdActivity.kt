package com.example.blu_e.login

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.blu_e.FindIdResponse
import com.example.blu_e.SignupResponse
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.databinding.ActivityFindIdBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FindIdActivity : AppCompatActivity() {
    lateinit var viewBinding : ActivityFindIdBinding
    private val api = RetroInterface.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityFindIdBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        var phoneNum : String?
        //뒤로가기(로그인 화면으로)
        viewBinding.backToCenterD.setOnClickListener {
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        //인증코드 발송
        var phoneNumSuccess = false
        var verifySuccess = false
        phoneNum = viewBinding.phoneNumber.text.toString()
        viewBinding.sendBtn.setOnClickListener {
            api.sendPhoneNum(phoneNum).enqueue(object : Callback<SignupResponse>{
                override fun onResponse(
                    call: Call<SignupResponse>,
                    response: Response<SignupResponse>
                ) {
                    val responseData = response.body()
                    if (responseData != null) {
                        if(responseData.code == 1000){
                            phoneNumSuccess = true
                        }
                        else if(responseData.code == 2019){
                            //인증 문자 발송 실패
                        }
                    }
                }

                override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }

        viewBinding.findIdBtn.setOnClickListener {
            if(phoneNumSuccess) {
                val verifyEncode = viewBinding.certificateNum.toString()
                Log.d("인증코드", "${verifyEncode}")
                api.verifyCode(phoneNum, verifyEncode).enqueue(object : Callback<SignupResponse> {
                    override fun onResponse(
                        call: Call<SignupResponse>,
                        response: Response<SignupResponse>
                    ) {
                        val responseData = response.body()
                        if (responseData != null) {
                            if (responseData.code == 1000) {
                                //인증번호 성공
                                verifySuccess = true
                            } else if (responseData.code == 2020) {
                                //인증  실패
                            }
                        }
                    }

                    override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })
            }
            if(verifySuccess){
                   api.findId(phoneNum).enqueue(object : Callback<FindIdResponse>{
                       override fun onResponse(
                           call: Call<FindIdResponse>,
                           response: Response<FindIdResponse>
                       ) {
                           val responseData = response.body()
                           if (responseData != null) {
                               if(responseData.code == 1000){
                                   val id = responseData.result.id
                                   val builder = AlertDialog.Builder(this@FindIdActivity)
                                       .setTitle("회원님의 아이디는\n"+id+"입니다.")
                                       .setPositiveButton("확인",
                                           DialogInterface.OnClickListener{ dialog, which ->
                                               Toast.makeText(this@FindIdActivity, "확인", Toast.LENGTH_SHORT).show()
                                           })
                                   builder.show()
                               }

                           }
                       }

                        override fun onFailure(call: Call<FindIdResponse>, t: Throwable) {
                            TODO("Not yet implemented")
                        }

                   })

            }
        }

    }
}