package com.example.blu_e.customercenter

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.blu_e.MainActivity
import com.example.blu_e.data.ResponseData
import com.example.blu_e.data.accusation.Report
import com.example.blu_e.databinding.FragmentAccusationBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.sql.Timestamp

class AccusationFragment : Fragment() {
    private lateinit var mContext: MainActivity
    private lateinit var viewBinding: FragmentAccusationBinding
//    private val api = RetroInterface.create()
    private lateinit var report: Report

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentAccusationBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewBinding.backToCenterA.setOnClickListener {
            mContext.openFragment(5)
        }
        viewBinding.btnAddPic.setOnClickListener {
            selectGallery()
        }
        viewBinding.btnAdd.setOnClickListener {
            val title: String = viewBinding.accusTitle.text.toString()
            val contents: String = viewBinding.accusContent.text.toString()
            val image = mContext.profileImageBase64.toString()

            /*api.reportMember(0, title, contents, image).enqueue(object: Callback<ResponseData> {
                override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                    val body = response.body()?: return
                    if(body != null) {
                        if(body.code == 1000) {
                            Log.d("신고 글 등록하기: ", body.message)

                            val builder = AlertDialog.Builder(mContext)
                            builder
                                .setTitle("신고")
                                .setMessage("신고 글 등록이 완료되었습니다. ")
                                .setPositiveButton("확인",
                                    DialogInterface.OnClickListener { dialog, id ->
                                        mContext!!.openFragment(5)
                                    })
                            builder.show()
                        } else if(body.code == 2400 || body.code == 2401 || body.code == 2402 || body.code == 2403 || body.code == 2404) {
                            Log.d("신고 글 등록하기: ", body.message)
                            Toast.makeText(mContext, body.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                    Log.d("신고 글 등록하기: ", "실패")
                }
            })*/
        }
    }

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
        }
    }

}