package com.example.blu_e.mentoring

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.blu_e.data.mainPage.NewMentorData
import com.example.blu_e.databinding.ActivityMentorHistoryBinding
import com.example.blu_e.databinding.ActivityProfileBinding

class ProfileActivity: AppCompatActivity() {
    lateinit var viewBinding: ActivityProfileBinding

    companion object {
        fun newInstance(mentorList: ArrayList<NewMentorData>, mentorId: Int) = ProfileActivity().apply {
            var arguments = Bundle().apply {
                putSerializable("mentorList", mentorList)
                putInt("mentorId", mentorId)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}
