package com.example.blu_e.mypage

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.blu_e.MainActivity
import com.example.blu_e.data.ResponseData
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.databinding.FragmentMentorChangePasswdBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MentorPasswdChangeFragment:Fragment() {
    private val api = RetroInterface.create() //retrofit 객체
    private lateinit var mContext: MainActivity
    private lateinit var viewBinding: FragmentMentorChangePasswdBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMentorChangePasswdBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//뒤로가기
        viewBinding.backToPageMentor.setOnClickListener {
            mContext!!.openFragment(5)
        }
//비밀번호 변경
        viewBinding.btnChangePwd.setOnClickListener {
            var password = viewBinding.passwdMentor.text.toString()
            var password2 = viewBinding.passwdReMentor.text.toString()
            val builder = AlertDialog.Builder(mContext)
            api.changePasswdMentor(
                password,
                password2
            ).enqueue(object :
                Callback<ResponseData> {
                override fun onResponse(
                    call: Call<ResponseData>,
                    response: Response<ResponseData>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body() ?: return
                        if (body.code == 1000) {
                            Log.d("비밀번호 변경", "성공")
                            //알림창 띄우면 뒤로가기
                            builder
                                .setTitle("비밀번호 변경")
                                .setMessage("비밀번호 변경이 완료되었습니다. ")
                                .setPositiveButton("확인",
                                    DialogInterface.OnClickListener { dialog, id ->
                                        mContext!!.openFragment(5)
                                    })
                            builder.show()
                        } else if (body.code == 2512) {
                            Log.d("비밀번호 변경", "비밀번호 미입력")
                            //알림창 띄우면 뒤로가기
                            builder
                                .setTitle("비밀번호 변경")
                                .setMessage("비밀번호를 입력해주세요.")
                                .setPositiveButton("확인",
                                    DialogInterface.OnClickListener { dialog, id ->
                                        mContext!!.openFragment(16)
                                    })
                            builder.show()
                        } else if (body.code == 2513) {
                            Log.d("비밀번호 변경", "비밀번호 20자리 이상")
                            //알림창 띄우면 뒤로가기
                            builder
                                .setTitle("비밀번호 변경")
                                .setMessage("비밀번호가 20자리 미만으로 입력해주세요.")
                                .setPositiveButton("확인",
                                    DialogInterface.OnClickListener { dialog, id ->
                                        mContext!!.openFragment(16)
                                    })
                            builder.show()
                        } else if (body.code == 3501) {
                            Log.d("비밀번호 변경", "비밀번호 동일하지 않음")
                            //알림창 띄우면 뒤로가기
                            builder
                                .setTitle("비밀번호 변경")
                                .setMessage("새 비밀번호를 동일하게 입력해주세요.")
                                .setPositiveButton("확인",
                                    DialogInterface.OnClickListener { dialog, id ->
                                        mContext!!.openFragment(16)
                                    })
                            builder.show()
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                    Log.e("비밀번호 수정", "failure")
                }

            })
        }
    }

    private fun hideKeyBoard() {
        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
    }
}