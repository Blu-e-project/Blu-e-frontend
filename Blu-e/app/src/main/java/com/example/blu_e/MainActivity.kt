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
import com.example.blu_e.customercenter.AccusationFragment
import com.example.blu_e.customercenter.CenterFragment
import com.example.blu_e.customercenter.FaqDetailFragment
import com.example.blu_e.customercenter.QuestionFormFragment
import com.example.blu_e.databinding.ActivityMainBinding
import com.example.blu_e.mainPage.*
import com.example.blu_e.mypage.MentorChangeInfoFragment
import com.example.blu_e.mypage.MentorPasswdChangeFragment
import java.io.ByteArrayOutputStream
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMainBinding
    var profileImageBase64: String? = null
    var role: Int = MainApplication.prefs.getString("role", "").toInt()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        if(role == 1) {
            supportFragmentManager
                .beginTransaction()
                .replace(viewBinding.containerFragment.id, HomeMentorFragment())
                .commitAllowingStateLoss()
        }
        else{
            supportFragmentManager
                .beginTransaction()
                .replace(viewBinding.containerFragment.id, HomeMenteeFragment())
                .commitAllowingStateLoss()
        }
        viewBinding.navBottom.run {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.menu_home -> {
                        if(role == 1) {
                            supportFragmentManager
                                .beginTransaction()
                                .replace(viewBinding.containerFragment.id, HomeMentorFragment())
                                .commitAllowingStateLoss()
                        }
                        else{
                            supportFragmentManager
                                .beginTransaction()
                                .replace(viewBinding.containerFragment.id, HomeMenteeFragment())
                                .commitAllowingStateLoss()
                        }
                    }
                    R.id.menu_group -> {
                        if(role == 1) {
                            supportFragmentManager
                                .beginTransaction()
                                .replace(
                                    viewBinding.containerFragment.id,
                                    HomeMentorButtonFragment()
                                )
                                .commitAllowingStateLoss()
                        }
                        else{
                            supportFragmentManager
                                .beginTransaction()
                                .replace(
                                    viewBinding.containerFragment.id,
                                    HomeMenteeButtonFragment()
                                )
                                .commitAllowingStateLoss()
                        }
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

    //Fragment ?????? ??????
    fun openFragment(n: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        when(n) {
            1 -> transaction.replace(viewBinding.containerFragment.id, FaqDetailFragment())
            2 -> transaction.replace(viewBinding.containerFragment.id, CenterFragment())
            3 -> transaction.replace(viewBinding.containerFragment.id, QuestionFormFragment())
            4 -> transaction.replace(viewBinding.containerFragment.id, AccusationFragment())
            5 -> transaction.replace(viewBinding.containerFragment.id, MyPageFragment())

            //main page
            6 -> transaction.replace(viewBinding.containerFragment.id, HomeNewMenteeFragment()) //????????? ????????? ?????????
            7 -> transaction.replace(viewBinding.containerFragment.id, HomeRecruitMentorFragment()) //????????? ????????? ?????????
            //8 -> transaction.replace(viewBinding.containerFragment.id, RecruitMenteeFragment()) //?????? ?????????
            9 -> transaction.replace(viewBinding.containerFragment.id, HomeNewMentorFragment()) //????????? ????????? ?????????
            10 -> transaction.replace(viewBinding.containerFragment.id, HomeRecruitMenteeFragment()) //????????? ????????? ?????????
            11 -> transaction.replace(viewBinding.containerFragment.id, HomeQuestionFragment()) //????????? ????????? ?????????
            12 -> transaction.replace(viewBinding.containerFragment.id, HomeMentorFragment()) //????????? ????????? ?????????_?????? ????????????
            13 -> transaction.replace(viewBinding.containerFragment.id, HomeMentorButtonFragment()) //?????? ????????? ??????(????????? ?????? ??????)
            14 -> transaction.replace(viewBinding.containerFragment.id, HomeMentorFragment()) //?????? ???
            15 -> transaction.replace(viewBinding.containerFragment.id, HomeMenteeFragment()) //?????? ???

            //mypage ??? ??????
            16 -> transaction.replace(viewBinding.containerFragment.id, MentorPasswdChangeFragment()) //???????????? ??????
            17->transaction.replace(viewBinding.containerFragment.id, MentorChangeInfoFragment()) //??? ?????? ??????
        }
        transaction.addToBackStack(null);
        transaction.commit()
    }

    //????????? ?????? ?????? ??? Base64 ?????????
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
                Toast.makeText(this, "???????????? ?????????????????????!", Toast.LENGTH_SHORT).show()
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