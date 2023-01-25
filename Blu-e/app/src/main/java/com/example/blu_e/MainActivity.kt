package com.example.blu_e

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.blu_e.databinding.ActivityMainBinding
import com.example.blu_e.databinding.ActivitySignUpBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding:ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(viewBinding.containerFragment.id, HomeFragment())
            .commitAllowingStateLoss()

        viewBinding.navBottom.run {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.menu_home -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(viewBinding.containerFragment.id, HomeFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_group -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(viewBinding.containerFragment.id, GroupFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_noti -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(viewBinding.containerFragment.id, NotiFragment())
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
                    .replace(viewBinding.containerFragment.id, HomeFragment())
                    .commitAllowingStateLoss()
            }

        }

    }

    //질문 선택 <-> 상세화면
    fun openFragment(n: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        when(n) {
            1 -> transaction.replace(viewBinding.containerFragment.id, QuestionDetailFragment())
            2 -> transaction.replace(viewBinding.containerFragment.id, CenterFragment())
            3 -> transaction.replace(viewBinding.containerFragment.id, QuestionFormFragment())
        }
        transaction.addToBackStack(null);
        transaction.commit()
    }
}