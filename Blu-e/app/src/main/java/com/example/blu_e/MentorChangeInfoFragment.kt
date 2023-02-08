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
import com.example.blu_e.databinding.FragmentMentorChangeInfoBinding
import java.io.ByteArrayOutputStream
import java.io.InputStream

class MentorChangeInfoFragment : Fragment() {
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