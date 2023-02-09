package com.example.blu_e

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blu_e.databinding.RecyclerviewMentorCardBinding

class MyMenteePickRVAdapter(private val dataList: ArrayList<MyMenteePickItem> = arrayListOf(), val context: Context):
RecyclerView.Adapter<MyMenteePickRVAdapter.ViewHolder>(){
    inner class ViewHolder(private val viewBinding: RecyclerviewMentorCardBinding): RecyclerView.ViewHolder(viewBinding.root){
        fun bind(data:MyMenteePickItem, context: Context){
            viewBinding.mentorCardTitle.text = data.area
            viewBinding.mentorCardDesiredSubject.text = data.subject
            viewBinding.mentorCardDesiredPeriod.text = data.period.toString()
            viewBinding.mentorCardMethod.text = data.mentoringMethod.toString()
            viewBinding.mentorCardGender.text= data.wishGender.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
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