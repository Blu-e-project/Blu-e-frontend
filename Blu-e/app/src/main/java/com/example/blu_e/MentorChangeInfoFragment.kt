package com.example.blu_e

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.blu_e.data.ResponseData
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.databinding.FragmentMentorChangeInfoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.InputStream

class MentorChangeInfoFragment : Fragment() {
    private val api = RetroInterface.create() //retrofit 객체
    private lateinit var mContext: MainActivity
    private lateinit var viewBinding: FragmentMentorChangeInfoBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMentorChangeInfoBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//뒤로가기
        viewBinding.backToCenterAMentor.setOnClickListener {
            mContext!!.openFragment(5)
        }

        //viewBinding.imageEditBtnMentor.setOnClickListener {
        //    selectGallery()
        //}

        //갤러리 사진 선택
        viewBinding.imageEditBtnMentor.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            Log.d("이미지", "성공")
            activityResult.launch(intent)
        }
        //정보 수정 데이터 보내고 response 받아오기
        viewBinding.btnAddMentor.setOnClickListener {
            var name = viewBinding.nameMentor.text.toString()
            var nickname = viewBinding.nicknameMentor.text.toString()
            var birth= viewBinding.birthMentor.text.toString()
            var education= viewBinding.educationMentor.text.toString()
            var address= viewBinding.addrMentor.text.toString()
            var introduce= viewBinding.introduceMentor.text.toString()

            val builder = AlertDialog.Builder(mContext)
            api.changeMyinfoMentor("eUItOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjIsImlhdCI6M..", name, nickname, birth,
            education,address,introduce).enqueue(object :
                Callback<ResponseData> {
                override fun onResponse(
                    call: Call<ResponseData>,
                    response: Response<ResponseData>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body() ?: return
                        if (body.code == 1000) {
                            Log.d("정보 수정", "성공")
                            //알림창 띄우면 뒤로가기
                            builder
                                .setTitle("정보 수정")
                                .setMessage("정보 수정이 완료되었습니다. ")
                                .setPositiveButton("확인",
                                    DialogInterface.OnClickListener { dialog, id ->
                                        mContext!!.openFragment(5)
                                    })
                            builder.show()
                        } else if (body.code == 2501) {
                            Log.d("정보 수정", "이름 미입력")
                            //알림창 띄우면 뒤로가기
                            builder
                                .setTitle("정보 수정")
                                .setMessage("이름을 입력해주세요 ")
                                .setPositiveButton("확인",
                                    DialogInterface.OnClickListener { dialog, id ->
                                        mContext!!.openFragment(17)
                                    })
                            builder.show()
                        } else if (body.code == 2507) {
                            Log.d("정보 수정", "이름 너무길어")
                            //알림창 띄우면 뒤로가기
                            builder
                                .setTitle("정보 수정")
                                .setMessage("이름을 7자 이내로 입력해주세요 ")
                                .setPositiveButton("확인",
                                    DialogInterface.OnClickListener { dialog, id ->
                                        mContext!!.openFragment(17)
                                    })
                            builder.show()
                        }
                        else if (body.code == 2502) {
                            Log.d("정보 수정", "닉네임 미입력")
                            //알림창 띄우면 뒤로가기
                            builder
                                .setTitle("정보 수정")
                                .setMessage("닉네임을 입력해주세요 ")
                                .setPositiveButton("확인",
                                    DialogInterface.OnClickListener { dialog, id ->
                                        mContext!!.openFragment(17)
                                    })
                            builder.show()
                        } else if (body.code == 2508) {
                            Log.d("정보 수정", "닉네임 너무길어")
                            //알림창 띄우면 뒤로가기
                            builder
                                .setTitle("정보 수정")
                                .setMessage("닉네임을 7자 이내로 입력해주세요 ")
                                .setPositiveButton("확인",
                                    DialogInterface.OnClickListener { dialog, id ->
                                        mContext!!.openFragment(17)
                                    })
                            builder.show()
                        }
                        else if (body.code == 2503) {
                            Log.d("정보 수정", "생일 미입력")
                            //알림창 띄우면 뒤로가기
                            builder
                                .setTitle("정보 수정")
                                .setMessage("생일을 입력해주세요 ")
                                .setPositiveButton("확인",
                                    DialogInterface.OnClickListener { dialog, id ->
                                        mContext!!.openFragment(17)
                                    })
                            builder.show()
                        }
                        else if (body.code == 2504) {
                            Log.d("정보 수정", "학력 미입력")
                            //알림창 띄우면 뒤로가기
                            builder
                                .setTitle("정보 수정")
                                .setMessage("학력을 입력해주세요 ")
                                .setPositiveButton("확인",
                                    DialogInterface.OnClickListener { dialog, id ->
                                        mContext!!.openFragment(17)
                                    })
                            builder.show()
                        }
                        else if (body.code == 2509) {
                            Log.d("정보 수정", "학력 너무길어")
                            //알림창 띄우면 뒤로가기
                            builder
                                .setTitle("정보 수정")
                                .setMessage("학력을 20자 이내로 입력해주세요")
                                .setPositiveButton("확인",
                                    DialogInterface.OnClickListener { dialog, id ->
                                        mContext!!.openFragment(17)
                                    })
                            builder.show()
                        }
                        else if (body.code == 2505) {
                            Log.d("정보 수정", "실거주지 미입력")
                            //알림창 띄우면 뒤로가기
                            builder
                                .setTitle("정보 수정")
                                .setMessage("실거주지를 입력해주세요 ")
                                .setPositiveButton("확인",
                                    DialogInterface.OnClickListener { dialog, id ->
                                        mContext!!.openFragment(17)
                                    })
                            builder.show()
                        }
                        else if (body.code == 2510) {
                            Log.d("정보 수정", "실거주지 너무길어")
                            //알림창 띄우면 뒤로가기
                            builder
                                .setTitle("정보 수정")
                                .setMessage("실거주지는 50자 이내로 입력해주세요 ")
                                .setPositiveButton("확인",
                                    DialogInterface.OnClickListener { dialog, id ->
                                        mContext!!.openFragment(17)
                                    })
                            builder.show()
                        }
                        else if (body.code == 2506) {
                            Log.d("정보 수정", "자기소개 미입력")
                            //알림창 띄우면 뒤로가기
                            builder
                                .setTitle("정보 수정")
                                .setMessage("자기소개를 입력해주세요 ")
                                .setPositiveButton("확인",
                                    DialogInterface.OnClickListener { dialog, id ->
                                        mContext!!.openFragment(17)
                                    })
                            builder.show()
                        }
                        else if (body.code == 2511) {
                            Log.d("정보 수정", "자기소개 너무길어")
                            //알림창 띄우면 뒤로가기
                            builder
                                .setTitle("정보 수정")
                                .setMessage("자기소개는 100자리 이하로 입력해주세요 ")
                                .setPositiveButton("확인",
                                    DialogInterface.OnClickListener { dialog, id ->
                                        mContext!!.openFragment(17)
                                    })
                            builder.show()
                        }
                        else if (body.code == 3500) {
                            Log.d("정보 수정", " 닉네임 중복")
                            //알림창 띄우면 뒤로가기
                            builder
                                .setTitle("정보 수정")
                                .setMessage("중복된 닉네임입니다")
                                .setPositiveButton("확인",
                                    DialogInterface.OnClickListener { dialog, id ->
                                        mContext!!.openFragment(17)
                                    })
                            builder.show()
                        }
                    }
                }
                override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                    Log.e("정보 수정", "failure")
                }

            })
        }

    }

    //갤러리 이미지 불러오기
    private val activityResult: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {

        if (it.resultCode == AppCompatActivity.RESULT_OK && it.data != null) {
            //값 담기
            val uri = it.data!!.data
            Log.d("이미지", "${uri}")
            //화면에 보여주기
            Glide.with(this)
                .load(uri)
                .into(viewBinding.imageMentor)
        }
    }
}
    /*
    //갤러리 이미지 불러오기 권한 묻기
    private fun selectGallery() {
        var writePermission = ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        var readPermission = ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE)

        if (writePermission == PackageManager.PERMISSION_DENIED || readPermission == PackageManager.PERMISSION_DENIED) {
            // 권한 없어서 요청
            ActivityCompat.requestPermissions(mContext, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE), 100)
        } else {
            // 권한 있음
            var intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            intent.type = "image/*"
            mContext.imageResult.launch(intent)

            //프로필 전환 코드
        }
    }
    */