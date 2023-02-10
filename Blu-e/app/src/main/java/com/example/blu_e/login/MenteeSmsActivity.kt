package com.example.blu_e.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blu_e.LoginResponse
import com.example.blu_e.R
import com.example.blu_e.SignupResponse
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.databinding.ActivityMenteeSmsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenteeSmsActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMenteeSmsBinding
    private val api = RetroInterface.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMenteeSmsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //인증번호 요청
        var phoneNumSuccess = false
        val phoneNum = viewBinding.phoneNumber.text.toString()
        viewBinding.smsBtn.setOnClickListener {
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

        viewBinding.menteeInfoBtn.setOnClickListener {
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
                                var intent = Intent(this@MenteeSmsActivity, MenteeInfoActivity::class.java)
                                intent.putExtra("phoneNum", phoneNum)
                                startActivity(intent)
                            }
                            else if(responseData.code == 2019){
                                //인증 문자 발송에 실패했습니다.
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