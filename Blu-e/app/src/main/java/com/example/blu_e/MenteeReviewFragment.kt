package com.example.blu_e

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.blu_e.databinding.FragmentReviewMenteeBinding
import com.example.blu_e.databinding.FragmentReviewMentorBinding

class MenteeReviewFragment : Fragment() {
    private lateinit var mContext: MainActivity

    private lateinit var viewBinding: FragmentReviewMenteeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentReviewMenteeBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewBinding.backToCenterAMentee.setOnClickListener{
            mContext!!.openFragment(5)
        }

        viewBinding.btnAddMentee.setOnClickListener {
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