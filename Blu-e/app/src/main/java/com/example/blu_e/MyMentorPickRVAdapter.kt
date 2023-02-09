package com.example.blu_e

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blu_e.databinding.RecyclerviewMentorCardBinding
import com.example.blu_e.databinding.RecyclerviewQuestionCardBinding

class MyMentorPickRVAdapter(private val dataList: ArrayList<MyMentorPickItem> = arrayListOf(), val context: Context):
    RecyclerView.Adapter<MyMentorPickRVAdapter.ViewHolder>(){
    inner class ViewHolder (private val viewBinding: RecyclerviewMentorCardBinding) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(data:MyMentorPickItem, context:Context){
            viewBinding.mentorCardTitle.text = data.area
            viewBinding.mentorCardDesiredSubject.text = data.subject
            viewBinding.mentorCardDesiredPeriod.text = data.period.toString()
            viewBinding.mentorCardMethod.text = data.mentoringMethod.toString()
            viewBinding.mentorCardGender.text= data.wishGender.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ProbByMeRVAdapter.ViewHolder(LayoutInflater.from(parent.context))
//            .inflate(R.layout.recyclerview_question_card, parent,false)
        val viewBinding = RecyclerviewMentorCardBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position], context)
    }

    override fun getItemCount(): Int {
        return dataList.count()
    }

}