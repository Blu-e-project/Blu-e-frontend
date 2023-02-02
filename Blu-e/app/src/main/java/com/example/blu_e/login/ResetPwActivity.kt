package com.example.blu_e.login

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.blu_e.R
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
            var pw1 = viewBinding.newPw1.text.toString()
            var pw2 = viewBinding.newPw2.text.toString()

            if(pw1 == pw2){
                //새 비밀번호 데이터에 전달
                //알림창 띄움 확인 누르면 로그인화면으로
                val builder = AlertDialog.Builder(this)
                    .setTitle("비밀번호 재설정")
                    .setMessage("비밀번호 재설정이 완료 되었습니다.")
                    .setPositiveButton("확인",
                        DialogInterface.OnClickListener{ dialog, which ->
                            Toast.makeText(this, "확인", Toast.LENGTH_SHORT).show()
                            var intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                        })
                builder.show()
            }
        }
    }
}