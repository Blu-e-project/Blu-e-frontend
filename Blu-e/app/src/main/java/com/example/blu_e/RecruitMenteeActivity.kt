package com.example.blu_e

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.blu_e.databinding.ActivityRecruitMenteeBinding

class RecruitMenteeActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityRecruitMenteeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityRecruitMenteeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.createRecruitBtn.setOnClickListener {
            val title = viewBinding.title.text.toString()
            val contents = viewBinding.content.text.toString()
            val subject = viewBinding.subject.text.toString()
            val area = viewBinding.region.text.toString()
            val mentoringMethod = viewBinding.mentoringMethod.text.toString()
            val menteeLevel = viewBinding.menteeLevel.text.toString()
            val periodStart = viewBinding.startPerioid.text.toString()
            val periodEnd = viewBinding.endPerioid.text.toString()
            val wishGender = viewBinding.gender.text.toString()
            Log.d("msg", "${title}")
//            api.recruitMentor(title, contents, subject,area,mentoringMethod,menteeLevel,periodStart,periodEnd,wishGender)
//                .enqueue(object :Callback<CreateRecruitResponse>{
//                    override fun onResponse(call: Call<CreateRecruitResponse>, response: Response<CreateRecruitResponse>
//                    ) {
//                        val responseData = response.body()
//                        if (responseData!=null){
//                            //create성공
//                            if(responseData.code == 1000){
//
//                            }
//                            else{
//                                //아니면 message 띄우기
//                            }
//                        }
//                    }
//
//                    override fun onFailure(call: Call<CreateRecruitResponse>, t: Throwable) {
//                        TODO("Not yet implemented")
//                    }
//
//                })

            val builder = AlertDialog.Builder(this)
                .setTitle("멘티 구인글")
                .setMessage("멘티 구인 글 등록이 완료 되었습니다")
                .setPositiveButton("확인",
                    DialogInterface.OnClickListener{ dialog, which ->
                        Toast.makeText(this, "확인", Toast.LENGTH_SHORT).show()
                        //어느화면으로 이동할지
                    })
            builder.show()
        }
    }
}