package com.example.blu_e

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.blu_e.databinding.RecyclerviewQuestionCardBinding

class ProbSolByMeRVAdapter(private val dataList: ArrayList<ProbSolByMeItem> = arrayListOf(), val context: Context):
    RecyclerView.Adapter<ProbSolByMeRVAdapter.ViewHolder>() {
        inner class ViewHolder(private val viewBinding: RecyclerviewQuestionCardBinding) : RecyclerView.ViewHolder(viewBinding.root){

            fun bind(data: ProbSolByMeItem, context: Context){
                viewBinding.questionCardSubject.text = data.subject.toString()
                viewBinding.questionCardProblem.text = data.problem.toString()
                viewBinding.questionCardUnit.text = data.unit.toString()
                viewBinding.questionCardContents.text = data.contents.toString()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = RecyclerviewQuestionCardBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position], context)
    }

    override fun getItemCount(): Int {
        return dataList.count()
    }

}