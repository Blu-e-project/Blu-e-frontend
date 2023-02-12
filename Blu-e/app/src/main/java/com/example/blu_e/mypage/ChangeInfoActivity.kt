package com.example.blu_e.mypage

import android.content.DialogInterface
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.blu_e.MainActivity
import com.example.blu_e.data.ResponseData
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.databinding.ActivityChangeInfoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ChangeInfoActivity : AppCompatActivity() {
    private val api = RetroInterface.create()
    lateinit var uri: Uri
    var profileImageBase64: String? = null
    lateinit var viewBinding: ActivityChangeInfoBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityChangeInfoBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //뒤로가기(멘티 회원가입 페이지로)
        viewBinding.backToMypage.setOnClickListener {
            finish()
        }
        //갤러리 사진 선택
        viewBinding.imageEditBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            Log.d("이미지", "성공")
            activityResult.launch(intent)
            api.changeImage(profileImageBase64).enqueue(object:Callback<ResponseData> {
                override fun onResponse(
                    call: Call<ResponseData>,
                    response: Response<ResponseData>
                ) {
                    val responseData = response.body()
                    if (responseData != null) {
                        if (responseData.code == 1000) {
                            val builder = AlertDialog.Builder(this@ChangeInfoActivity)
                            builder.setTitle("프로필 사진 수정")
                                .setMessage("프로필 사진 수정이 완료되었습니다.")
                                .setPositiveButton("확인",
                                    DialogInterface.OnClickListener { dialog, id ->
                                        finish()
                                    })
                            builder.show()
                        }

                    }
                }

                override fun onFailure(call: Call<ResponseData>, t: Throwable) {

                }
            })

        }
        //정보수정버튼클릭시
        /*viewBinding.btnChange.setOnClickListener {
            val builder = AlertDialog.Builder(this@ChangeInfoActivity)
            builder.setTitle("정보 수정")
                .setMessage("정보 수정이 완료되었습니다.")
                .setPositiveButton("확인",
                    DialogInterface.OnClickListener { dialog, id ->
                        finish()
                    })
            builder.show()

        }*/
        viewBinding.btnChange.setOnClickListener {
            var name = viewBinding.nameChange.text.toString()
            var nickname = viewBinding.nicknameChange.text.toString()
            //string ->LocalDate로 바꿔야함
            val birthStr = viewBinding.birthChange.text.toString()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val birth = LocalDate.parse(birthStr, formatter).atStartOfDay()
            val birthDate = birth.toLocalDate()
            var education = viewBinding.educationChange.text.toString()
            var address = viewBinding.addrChange.text.toString()
            var introduce = viewBinding.introduceChange.text.toString()
            /*Log.d("정보 수정", "변수에 담기 성공")
            Log.d("소개", "${introduce}")
            Log.d("이름", "${name}")
            Log.d("닉네임", "${nickname}")
            Log.d("생일", "${birthDate}")
            Log.d("학교", "${education}")
            Log.d("주소", "${address}")
            Log.d("소개", "${introduce}")*/

            api.changeMyinfoMentor(
                name, nickname, birthDate, education, address, introduce
            ).enqueue(object : Callback<ResponseData> {
                override fun onResponse(
                    call: Call<ResponseData>,
                    response: Response<ResponseData>
                ) {
                    val responseData = response.body()
                    if (responseData != null) {
                        if (responseData.code == 1000) {
                            val builder = AlertDialog.Builder(this@ChangeInfoActivity)
                            builder.setTitle("정보 수정")
                                .setMessage("정보 수정이 완료되었습니다.")
                                .setPositiveButton("확인",
                                    DialogInterface.OnClickListener { dialog, id ->
                                        finish()
                                    })
                            builder.show()
                        }

                    }
                }

                override fun onFailure(call: Call<ResponseData>, t: Throwable) {

                }
            })
        }
    }

    private val activityResult: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {

        if (it.resultCode == AppCompatActivity.RESULT_OK && it.data != null) {
            //값 담기
            uri = it.data!!.data!!
            Log.d("이미지", "${uri}")
            //화면에 보여주기
            Glide.with(this)
                .load(uri)
                .into(viewBinding.imageInfo)
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
            if (profileImageBase64 != null) {
                Toast.makeText(this, "이미지가 첨부되었습니다!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
