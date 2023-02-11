package com.example.blu_e

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blu_e.databinding.RecyclerviewQuestionCardBinding

class ProbByMeRVAdapter(private val dataList: ArrayList<ProbByMeItem> = arrayListOf()):
    RecyclerView.Adapter<ProbByMeRVAdapter.ViewHolder>()
{
    inner class ViewHolder (private val viewBinding: RecyclerviewQuestionCardBinding) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(data:ProbByMeItem){
            viewBinding.questionCardSubject.text = data.subject
            viewBinding.questionCardProblem.text = data.problem
            viewBinding.questionCardUnit.text = data.unit
            viewBinding.questionCardContents.text = data.contents
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = RecyclerviewQuestionCardBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size

    }

}