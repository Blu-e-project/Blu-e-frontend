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
    //lateinit var phoneNum : String
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
     //   phoneNum = viewBinding.phoneNumber.text.toString()

        viewBinding.sendBtn.setOnClickListener {
            //phoneNum = viewBinding.phoneNumber.text.toString()
            //Log.d("번호", "${phoneNum}")
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
        }

        viewBinding.findIdBtn.setOnClickListener {
            verifySuccess = true
            if(verifySuccess) {
                api.findId("01029251128").enqueue(object : Callback<FindIdResponse> {
                    override fun onResponse(
                        call: Call<FindIdResponse>,
                        response: Response<FindIdResponse>
                    ) {
                        Log.d("인증 휴대폰", "아이디 받아오기")
                        val responseData = response.body()
                        if (responseData != null) {
                            if (responseData.code == 1000) {
                                val id = responseData.result.id
                                val builder = AlertDialog.Builder(this@FindIdActivity)
                                    .setTitle("회원님의 아이디는\n ${id}입니다.")
                                    .setPositiveButton("확인",
                                        DialogInterface.OnClickListener { dialog, which ->
                                            Toast.makeText(
                                                this@FindIdActivity,
                                                "확인",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        })
                                builder.show()
                            }

                        }
                    }

                    override fun onFailure(call: Call<FindIdResponse>, t: Throwable) {

                    }

                })
            }
//            if(phoneNumSuccess) {
//                val verifyEncode = viewBinding.certificateNum.text.toString()
//                Log.d("인증코드", "${verifyEncode}")
//                api.verifyCode(phoneNum, verifyEncode).enqueue(object : Callback<SignupResponse> {
//                    override fun onResponse(
//                        call: Call<SignupResponse>,
//                        response: Response<SignupResponse>
//                    ) {
//                        val responseData = response.body()
//                        if (responseData != null) {
//                            if (responseData.code == 1000) {
//                                //인증번호 성공
//                                Log.d("인증", "${verifySuccess}")
//                                verifySuccess = true
//                                if(verifySuccess){
//                                    Log.d("인증 휴대폰", "${phoneNum}")
//                                    api.findId(phoneNum).enqueue(object : Callback<FindIdResponse>{
//                                        override fun onResponse(
//                                            call: Call<FindIdResponse>,
//                                            response: Response<FindIdResponse>
//                                        ) {
//                                            Log.d("인증 휴대폰", "아이디 받아오기")
//                                            val responseData = response.body()
//                                            if (responseData != null) {
//                                                if(responseData.code == 1000){
//                                                    val id = responseData.result.id
//                                                    val builder = AlertDialog.Builder(this@FindIdActivity)
//                                                        .setTitle("회원님의 아이디는\n ${id}입니다.")
//                                                        .setPositiveButton("확인",
//                                                            DialogInterface.OnClickListener{ dialog, which ->
//                                                                Toast.makeText(this@FindIdActivity, "확인", Toast.LENGTH_SHORT).show()
//                                                            })
//                                                    builder.show()
//                                                }
//
//                                            }
//                                        }
//
//                                        override fun onFailure(call: Call<FindIdResponse>, t: Throwable) {
//
//                                        }
//
//                                    })
//
//                                }
//                            } else if (responseData.code == 2020) {
//                                //인증  실패
//                            }
//                        }
//                    }
//
//                    override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
//
//                    }
//
//                })
//            }

        }

    }
}