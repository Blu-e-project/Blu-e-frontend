package com.example.blu_e

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.blu_e.customercenter.AccusationFragment
import com.example.blu_e.customercenter.CenterFragment
import com.example.blu_e.customercenter.FaqDetailFragment
import com.example.blu_e.customercenter.QuestionFormFragment
import com.example.blu_e.databinding.ActivityMainBinding
import com.example.blu_e.mainPage.*
import java.io.ByteArrayOutputStream
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMainBinding
    var profileImageBase64: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(viewBinding.containerFragment.id, HomeMentorFragment())
            .commitAllowingStateLoss()

        viewBinding.navBottom.run {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.menu_home -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(viewBinding.containerFragment.id, HomeMentorFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_group -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(viewBinding.containerFragment.id, HomeMentorButtonFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_noti -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(viewBinding.containerFragment.id, MentorAlarmFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_mypage -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(viewBinding.containerFragment.id, MyPageFragment())
                            .commitAllowingStateLoss()
                    }
                }
                true
            }
            selectedItemId = R.id.menu_home
        }

        val centerButton = viewBinding.toolbar.customerCenter
        centerButton.setOnClickListener {
            centerButton.isSelected = centerButton.isSelected != true

            if (centerButton.isSelected) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(viewBinding.containerFragment.id, CenterFragment())
                    .commitAllowingStateLoss()
            }
            else {
                supportFragmentManager
                    .beginTransaction()
                    .replace(viewBinding.containerFragment.id, HomeMentorFragment())
                    .commitAllowingStateLoss()
            }
        }
    }

    //Fragment 화면 전환
    fun openFragment(n: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        when(n) {
            1 -> transaction.replace(viewBinding.containerFragment.id, FaqDetailFragment())
            2 -> transaction.replace(viewBinding.containerFragment.id, CenterFragment())
            3 -> transaction.replace(viewBinding.containerFragment.id, QuestionFormFragment())
            4 -> transaction.replace(viewBinding.containerFragment.id, AccusationFragment())
            5 -> transaction.replace(viewBinding.containerFragment.id, MyPageFragment())

            //main page
            6 -> transaction.replace(viewBinding.containerFragment.id, HomeNewMenteeFragment()) //새로운 멘티가 있어요
            7 -> transaction.replace(viewBinding.containerFragment.id, HomeRecruitMentorFragment()) //멘토를 구하고 있어요
            //8 -> transaction.replace(viewBinding.containerFragment.id, RecruitMenteeFragment()) //멘티 구인글
            9 -> transaction.replace(viewBinding.containerFragment.id, HomeNewMentorFragment()) //새로운 멘토가 있어요
            10 -> transaction.replace(viewBinding.containerFragment.id, HomeRecruitMenteeFragment()) //멘티를 구하고 있어요
            11 -> transaction.replace(viewBinding.containerFragment.id, HomeQuestionFragment()) //궁금한 문제가 있어요
            //12 -> transaction.replace(viewBinding.containerFragment.id, ) //궁금한 문제가 있어요_문제 등록하기
            //13 -> transaction.replace(viewBinding.containerFragment.id, ) //멘토 구인글
            14 -> transaction.replace(viewBinding.containerFragment.id, HomeMentorFragment()) //멘토 홈
            15 -> transaction.replace(viewBinding.containerFragment.id, HomeMenteeFragment()) //멘티 홈

            //mypage 내 이동
            16 -> transaction.replace(viewBinding.containerFragment.id, MentorPasswdChangeFragment()) //비밀번호 변경
            17->transaction.replace(viewBinding.containerFragment.id, MentorChangeInfoFragment()) //내 정보 수정
        }
        transaction.addToBackStack(null);
        transaction.commit()
    }

    //갤러리 사진 선택 후 Base64 인코딩
    val imageResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageUri = result.data?.data ?: return@registerForActivityResult
            val ins: InputStream? = imageUri?.let {
                contentResolver.openInputStream(it)
            }
            val img: Bitmap = BitmapFactory.decodeStream(ins)
            ins?.close()
            val resized = Bitmap.createScaledBitmap(img, 256, 256, true)
            val byteArrayOutputStream = ByteArrayOutputStream()
            resized.compress(Bitmap.CompressFormat.JPEG, 60, byteArrayOutputStream)
            val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
            val outStream = ByteArrayOutputStream()
            val res: Resources = resources
            profileImageBase64 = android.util.Base64.encodeToString(byteArray, Base64.NO_WRAP)
            if (profileImageBase64 != null) {
                Toast.makeText(this, "이미지가 첨부되었습니다!", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)

        if(currentFocus is EditText) {
            currentFocus!!.clearFocus()
        }

        return super.dispatchTouchEvent(ev)
    }
}
