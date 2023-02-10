package com.example.blu_e.login

import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.blu_e.SignupResponse
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.databinding.ActivityMentorInfoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MentorInfoActivity : AppCompatActivity() {
    private val api = RetroInterface.create()
    lateinit var uri: Uri
    lateinit var profileImageBase64: String
    lateinit var viewBinding: ActivityMentorInfoBinding
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMentorInfoBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //뒤로가기(멘티 회원가입 페이지로)
        viewBinding.backToCenterD.setOnClickListener {
            var intent = Intent(this, MentorSignUpActivity::class.java)
            startActivity(intent)
        }
        //갤러리 사진 선택
        viewBinding.profileBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            Log.d("이미지", "성공")
            activityResult.launch(intent)
        }
        viewBinding.signUpBtn.setOnClickListener {
            //비번과 비번확인이 같은지 확인
            if(viewBinding.userPw.text.toString() == viewBinding.checkPw.text.toString()) {
                Log.d("회원가입", "비번 확인")
                //체크했는지 안했는지 확인
                if (viewBinding.agreeCb.isChecked) {
                    Log.d("회원가입", "동의")
                    val id = viewBinding.userId.text.toString()
                    val password = viewBinding.userPw.text.toString()
//                    //본인인증에서 번호 가져옴
//                    val phone = intent.getStringExtra("phoneNum").toString()
                    val phone = "01022221234"
                    val name = viewBinding.name.text.toString()
                    val nickname = viewBinding.nickname.text.toString()
                    //string ->LocalDate로 바꿔야함
                    val birthStr = viewBinding.birth.text.toString()
                    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                    val birth = LocalDate.parse(birthStr, formatter).atStartOfDay()
                    val birthDate = birth.toLocalDate()

                    Log.d("회원가입","${birth}")
                    Log.d("회원가입","${birthDate}")
                    val education = viewBinding.education.text.toString()
                    val department = viewBinding.major.text.toString()
                    val grade: Int? = null
                    val address = viewBinding.addr.text.toString()
                    val introduce = viewBinding.introduceMent.text.toString()
                    val role = 1
                    val createAt = LocalDate.now()
                    val updateAt = LocalDate.now()
                    val status = 1

                    Log.d("회원가입","api전")
                    api.signUp(id, password,phone, name,nickname,birthDate,education,department, grade,address, introduce,role,createAt,updateAt,status, profileImageBase64)
                        .enqueue(object :Callback<SignupResponse>{
                            override fun onResponse(
                                call: Call<SignupResponse>,
                                response: Response<SignupResponse>
                            ) {
                                val responseData = response.body()
                                if (responseData != null) {
                                    if(responseData.code == 1000){
                                        //성공
                                        var mintent = Intent(this@MentorInfoActivity, MentorSignUpSuccessActivity::class.java)
                                        startActivity(mintent)
                                    } else{
                                        val msg = when(responseData.code) {
                                            2001 -> "아이디를 입력해주세요"
                                            2002 -> "아이드는 35자리로 이하로 입력해주세요."
                                            2003 -> "비밀번호를 입력하세요"
                                            else -> {}
                                        }
                                    }
                                }
                            }

                            override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                                TODO("Not yet implemented")
                            }
                        })
                }
            }
            else{

            }
        }
    }

    private val activityResult: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {

        if(it.resultCode == RESULT_OK && it.data != null){
            //값 담기
            uri = it.data!!.data!!
            Log.d("이미지", "${uri}")
            //화면에 보여주기
            Glide.with(this)
                .load(uri)
                .into(viewBinding.profile)
            val ins: InputStream? = uri?.let {
                contentResolver.openInputStream(uri)
            }
            val img: Bitmap = BitmapFactory.decodeStream(ins)
            ins?.close()
            val resized = Bitmap.createScaledBitmap(img, 256, 256, true)
            val byteArrayOutputStream = ByteArrayOutputStream()
            resized.compress(Bitmap.CompressFormat.JPEG, 60, byteArrayOutputStream)
            val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
            val outStream = ByteArrayOutputStream()
            val res: Resources = resources
            profileImageBase64 = Base64.encodeToString(byteArray, Base64.NO_WRAP)
            if(profileImageBase64 != null) {
                Toast.makeText(this, "이미지가 첨부되었습니다!", Toast.LENGTH_SHORT).show()
            }
    }
}
}