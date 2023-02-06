package com.example.blu_e.mainPage

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.blu_e.data.mainPage.NewMentorData
import com.example.blu_e.databinding.RecyclerviewNewMentorCardBinding

class NewMentorDataRVAdapter(private val dataList: ArrayList<NewMentorData> = arrayListOf()):
    RecyclerView.Adapter<NewMentorDataRVAdapter.DataViewHolder>() {
    inner class DataViewHolder(private val viewBinding: RecyclerviewNewMentorCardBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        @RequiresApi(Build.VERSION_CODES.O)

        fun bind(data: NewMentorData) {
            viewBinding.pickMentorId.text = data.pickMentorId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val viewBinding = RecyclerviewNewMentorCardBinding.inflate(LayoutInflater.from(parent.context), parent, false) //attachToParent 부모에 붙이겠니?? true하면 오류, layoutInflater를 바로 가져올 수 없어서 부모의 context정보를 이용

        return DataViewHolder(viewBinding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

}
