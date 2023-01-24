package com.example.blu_e

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.blu_e.databinding.FragmentAccusationBinding
import java.sql.Timestamp

class AccusationFragment : Fragment() {
    private lateinit var mContext: MainActivity
    private lateinit var viewBinding: FragmentAccusationBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentAccusationBinding.inflate(inflater, container, false)

        return viewBinding.root

        viewBinding.btnAdd.setOnClickListener {
            val builder = AlertDialog.Builder(mContext)
            builder
                .setTitle("멘티 신고")
                .setMessage("멘티 신고 글 등록이 완료되었습니다. ")
                .setPositiveButton("확인",
                    DialogInterface.OnClickListener { dialog, id ->
                        //retrofit
                        mContext!!.openFragment(2)
                    })
            builder.show()
        }
    }
}