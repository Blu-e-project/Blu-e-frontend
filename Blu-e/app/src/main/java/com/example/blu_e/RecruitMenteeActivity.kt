package com.example.blu_e

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.blu_e.data.ResponseData
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.data.mentoring.Pick
import com.example.blu_e.data.mentoring.PickResponse
import com.example.blu_e.databinding.ActivityRecruitMenteeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat

class RecruitMenteeActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityRecruitMenteeBinding
    private val api = RetroInterface.create()
    private var originalPostContents: Pick? = null
    private var isItFromUpdate: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityRecruitMenteeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        originalPostContents = intent.getSerializableExtra("contents") as Pick?
        isItFromUpdate = intent.getIntExtra("fromUpdate", 0)

        //이전화면으로
        viewBinding.backToCenter.setOnClickListener {
            val intent = Intent(this@RecruitMenteeActivity, MainActivity:: class.java)
            startActivity(intent)
        }

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

//            Log.d("msg", "${title}")
            if(isItFromUpdate == 0) {
                api.recruitMentee(title, contents, subject,area,mentoringMethod,menteeLevel,periodStart,periodEnd,wishGender)
                    .enqueue(object :Callback<CreateRecruitResponse>{
                        override fun onResponse(call: Call<CreateRecruitResponse>, response: Response<CreateRecruitResponse>
                        ) {
                            val responseData = response.body()
                            if (responseData!=null){
                                //create성공
                                if(responseData.code == 1000){
                                    val builder = AlertDialog.Builder(this@RecruitMenteeActivity)
                                        .setTitle("멘티 구인글")
                                        .setMessage("멘티 구인 글 등록이 완료 되었습니다")
                                        .setPositiveButton("확인",
                                            DialogInterface.OnClickListener{ dialog, which ->
                                                Toast.makeText(this@RecruitMenteeActivity, "확인", Toast.LENGTH_SHORT).show()
                                                val intent = Intent(this@RecruitMenteeActivity, MainActivity:: class.java)
                                                startActivity(intent)
                                                //어느화면으로 이동할지
                                            })
                                    builder.show()
                                }
                                else{
                                    var msg =responseData.message.toString()
                                    val builder = AlertDialog.Builder(this@RecruitMenteeActivity)
                                        .setTitle("멘티 구인글")
                                        .setMessage(msg)
                                        .setPositiveButton("확인",
                                            DialogInterface.OnClickListener{ dialog, which ->
                                                Toast.makeText(this@RecruitMenteeActivity, "확인", Toast.LENGTH_SHORT).show()
                                                //어느화면으로 이동할지
                                            })
                                    builder.show()
                                }
                            }
                        }

                        override fun onFailure(call: Call<CreateRecruitResponse>, t: Throwable) {
                            TODO("Not yet implemented")
                        }

                    })
            }
            else if (isItFromUpdate == 1) {
                /*api.updateAPostOfMentee("", 0, title, contents, subject,area,mentoringMethod,menteeLevel,periodStart,periodEnd,wishGender)
                    .enqueue(object: Callback<ResponseData> {
                        override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                            val body = response.body()?: return
                            if (body != null) {
                                if(body.code == 1000){
                                    Log.d("멘티 구인글 업데이트 하기", "성공")
                                    val builder = AlertDialog.Builder(this@RecruitMenteeActivity)
                                        .setTitle("멘티 구인글")
                                        .setMessage("멘티 구인 글 수정이 완료 되었습니다")
                                        .setPositiveButton("확인",
                                            DialogInterface.OnClickListener{ dialog, which ->
                                                //어느화면으로 이동할지
                                            })
                                    builder.show()
                                }
                                else if(body.code == 2600 || body.code == 2601 || body.code == 2602 || body.code == 2603 ||
                                    body.code == 2604 || body.code == 2605 || body.code == 2606 || body.code == 2607 ||
                                    body.code == 2608 || body.code == 2610 || body.code == 2611 || body.code == 2612 ||
                                    body.code == 2613 || body.code == 2614 || body.code == 2616 || body.code == 2617) {
                                    Log.d("멘티 구인글 업데이트 하기 실패: ", body.message)
                                    Toast.makeText(this@RecruitMenteeActivity, body.message, Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                        override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                            Log.d("멘티 구인글 업데이트 하기", "실패")
                        }
                })*/
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if(isItFromUpdate == 1) {
            //기존 값 넣어두기
            viewBinding.title.setText(originalPostContents!!.title)
            viewBinding.content.setText(originalPostContents!!.contents)
            viewBinding.subject.setText(originalPostContents!!.subject)
//                viewBinding.region.setText(originalPostContents.re)
            viewBinding.mentoringMethod.setText(originalPostContents!!.mentoringMethod)
            viewBinding.menteeLevel.setText(originalPostContents!!.menteeLevel)

            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val startTime = sdf.format(originalPostContents!!.periodStart)
            viewBinding.startPerioid.setText(startTime)
            val endTime = sdf.format(originalPostContents!!.periodEnd)
            viewBinding.endPerioid.setText(endTime)
            viewBinding.gender.setText(originalPostContents!!.wishGender)
        }
    }
}