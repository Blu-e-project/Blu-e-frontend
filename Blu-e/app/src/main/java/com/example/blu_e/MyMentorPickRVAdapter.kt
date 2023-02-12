package com.example.blu_e

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blu_e.databinding.RecyclerviewMentorCardBinding
import com.example.blu_e.databinding.RecyclerviewQuestionCardBinding
import com.example.blu_e.mentoring.RequestMentoringActivity

class MyMentorPickRVAdapter(private val dataList: ArrayList<MyMentorPickItem> = arrayListOf(), private val context: Context):
    RecyclerView.Adapter<MyMentorPickRVAdapter.ViewHolder>(){
    var pickId = 0
    inner class ViewHolder (private val viewBinding: RecyclerviewMentorCardBinding) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(data:MyMentorPickItem){
            viewBinding.mentorCardTitle.text = data.area
            viewBinding.mentorCardDesiredSubject.text = data.subject
            viewBinding.mentorCardDesiredPeriod.text = data.period.toString()
            viewBinding.mentorCardMethod.text = data.mentoringMethod.toString()
            viewBinding.mentorCardGender.text= data.wishGender.toString()
            pickId = data.pickId
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ProbByMeRVAdapter.ViewHolder(LayoutInflater.from(parent.context))
//            .inflate(R.layout.recyclerview_question_card, parent,false)
        val viewBinding = RecyclerviewMentorCardBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = dataList!![position]
        holder.bind(model)
        holder.itemView.setOnClickListener {
            Log.d("selected", position.toString())
            Log.d("pickId", dataList!![position].pickId.toString())
            var intent: Intent = Intent(context, RequestMentoringActivity::class.java)
            intent.putExtra("pickId", dataList!![position].pickId)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}