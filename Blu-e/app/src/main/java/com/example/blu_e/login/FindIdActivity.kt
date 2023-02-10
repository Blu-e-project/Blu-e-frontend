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
    lateinit var phoneNum : String
    var phoneNumSuccess = false
    var verifySuccess = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityFindIdBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //뒤로가기(로그인 화면으로)
        viewBinding.backToCenterD.setOnClickListener {
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        //인증코드 발송

//        viewBinding.sendBtn.setOnClickListener {
//            phoneNum = viewBinding.phoneNumber.text.toString()
//            Log.d("번호", "${phoneNum}")
//            api.sendPhoneNum(phoneNum).enqueue(object : Callback<SignupResponse>{
//                override fun onResponse(
//                    call: Call<SignupResponse>,
//                    response: Response<SignupResponse>
//                ) {
//                    val responseData = response.body()
//                    if (responseData != null) {
//                        if(responseData.code == 1000){
//                            phoneNumSuccess = true
//                            Log.d("번호", "성공")
//                        }
//                        else if(responseData.code == 2019){
//                            //인증 문자 발송 실패
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
//
//                }
//
//            })
//        }

        viewBinding.findIdBtn.setOnClickListener {
//            verifySuccess = true
            //if(verifySuccess) {
                api.findId("01029251128").enqueue(object : Callback<FindIdResponse> {
                    override fun onResponse(
                        call: Call<FindIdResponse>,
                        response: Response<FindIdResponse>
                    ) {
//                        Log.d("인증 휴대폰", "아이디 받아오기")
                        val responseData = response.body()
                        Log.d("id", "${responseData}")
                        if (responseData != null) {
                            if (responseData.code == 1000) {
                                Log.d("id", "${responseData.result.id.toString()}")
//                                Log.d("아이디", "성공")
//                                val id = responseData.result.id
//                                Log.d("아이디", id)
//                                Log.d("아이디", "${id}")
                            }
                        }
                        else{
                            Log.d("id", "데이터 없음")
                        }
                    }

                    override fun onFailure(call: Call<FindIdResponse>, t: Throwable) {

                    }

                })
            //}


        }

    }
}