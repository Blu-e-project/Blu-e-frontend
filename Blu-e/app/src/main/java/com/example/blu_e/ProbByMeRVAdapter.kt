package com.example.blu_e

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blu_e.databinding.RecyclerviewQuestionCardBinding

class ProbByMeRVAdapter(private val dataList: ArrayList<ProbByMeItem> = arrayListOf(), val context: Context):
    RecyclerView.Adapter<ProbByMeRVAdapter.ViewHolder>()
{
    inner class ViewHolder (private val viewBinding: RecyclerviewQuestionCardBinding) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(data:ProbByMeItem, context:Context){
            viewBinding.questionCardSubject.text = data.subject.toString()
            viewBinding.questionCardProblem.text = data.problem.toString()
            viewBinding.questionCardUnit.text = data.unit.toString()
            viewBinding.questionCardContents.text = data.contents.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ProbByMeRVAdapter.ViewHolder(LayoutInflater.from(parent.context))
//            .inflate(R.layout.recyclerview_question_card, parent,false)
        val viewBinding = RecyclerviewQuestionCardBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position], context)
    }

    override fun getItemCount(): Int {
        return dataList.size

    }

}