package com.example.blu_e.login

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.blu_e.R
import com.example.blu_e.SignupResponse
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.data.User
import com.example.blu_e.databinding.ActivityResetPwBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResetPwActivity : AppCompatActivity() {
    private val api = RetroInterface.create()
    lateinit var viewBinding: ActivityResetPwBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityResetPwBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //비밀번호 재설정
        viewBinding.resetPwBtn.setOnClickListener {
            //비밀번호와 비밀번호 확인이 같은지 체크
            var id = viewBinding.id.text.toString()
            var pw1 = viewBinding.newPw1.text.toString()
            var pw2 = viewBinding.newPw2.text.toString()
            val phoneNum = intent.getStringExtra("phoneNum").toString()
                //새 비밀번호 데이터에 전달
                api.resetPw(id, phoneNum, pw1,pw2).enqueue(object : Callback<SignupResponse>{
                    override fun onResponse(
                        call: Call<SignupResponse>,
                        response: Response<SignupResponse>
                    ) {
                        //알림창 띄움 확인 누르면 로그인화면으로
                        val builder = AlertDialog.Builder(this@ResetPwActivity)
                            .setTitle("비밀번호 재설정")
                            .setMessage("비밀번호 재설정이 완료 되었습니다.")
                            .setPositiveButton("확인",
                                DialogInterface.OnClickListener{ dialog, which ->
                                    Toast.makeText(this@ResetPwActivity, "확인", Toast.LENGTH_SHORT).show()
                                    var intent = Intent(this@ResetPwActivity, LoginActivity::class.java)
                                    startActivity(intent)
                                })
                        builder.show()
                    }

                    override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })

        }
    }
}