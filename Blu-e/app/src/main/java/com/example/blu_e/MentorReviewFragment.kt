package com.example.blu_e

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.blu_e.databinding.FragmentReviewMentorBinding
import java.sql.Timestamp

class MentorReviewFragment : Fragment() {
    private lateinit var mContext: MainActivity //mainactivity에서 넘어가게 하려고
    private lateinit var viewBinding: FragmentReviewMentorBinding

    override fun onAttach(context: Context) { //mainactivity
        super.onAttach(context)
        mContext = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentReviewMentorBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewBinding.backToCenterAMentor.setOnClickListener{
            mContext!!.openFragment(5)
        }

        viewBinding.btnAddMentor.setOnClickListener {
            val builder = AlertDialog.Builder(mContext)
            builder
                .setTitle("리뷰 작성")
                .setMessage("리뷰등록이 완료되었습니다. ")
                .setPositiveButton("확인",
                    DialogInterface.OnClickListener { dialog, id ->
                        mContext!!.openFragment(5)
                    })
            builder.show()
        }
    }
}