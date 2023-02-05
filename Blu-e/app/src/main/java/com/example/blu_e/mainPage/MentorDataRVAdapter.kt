package com.example.blu_e.mainPage

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.blu_e.data.MentorData
import com.example.blu_e.databinding.RecyclerviewMentorCardBinding

/*
class MentorDataRVAdapter(private val items: ArrayList<MentorData>) : RecyclerView.Adapter<MentorDataRVAdapter.ViewHolder>() {
    //각 항목에 필요한 기능 구현, ViewHolder 반환
    inner class ViewHolder(private val viewBinding: RecyclerviewMentorCardBinding): RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(data: MentorData) {
            viewBinding.menteeCardTitle.text = data.title
            viewBinding.menteeCardDesiredSubject.text = data.sub
            viewBinding.menteeCardDesiredPeriod.text = data.date
            viewBinding.menteeCardMethod.text = data.method
            viewBinding.menteeCardArea.text = data.region
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = RecyclerviewMentorCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        Log.e("엥", "1")

        return ViewHolder(viewBinding)
    }

    //항목 뷰에 데이터 연결
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = items[position]
        holder.bind(model)

        Log.e("엥", "2")
    }

    //아이템 개수
    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }
    private lateinit var itemClickListener: ItemClickListener
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}
*/
class MentorDataRVAdapter(private val dataList: ArrayList<MentorData> = arrayListOf()):
    RecyclerView.Adapter<MentorDataRVAdapter.DataViewHolder>() {
    inner class DataViewHolder(private val viewBinding:RecyclerviewMentorCardBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(data:MentorData) { //viewHolder가 어떤걸 표시할 때 호출 시켜주는함수 data class를 인자값으로, 뷰 설정
            viewBinding.mentorCardTitle.text = data.title
            viewBinding.mentorCardDesiredSubject.text = data.subject
            viewBinding.mentorCardDesiredStartPeriod.text = data.periodStart
            viewBinding.mentorCardDesiredEndPeriod.text = data.periodEnd
            viewBinding.mentorCardMethod.text = data.mentoringMethod
            viewBinding.mentorCardGender.text = data.mentorGender
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val viewBinding = RecyclerviewMentorCardBinding.inflate(LayoutInflater.from(parent.context), parent, false) //attachToParent 부모에 붙이겠니?? true하면 오류, layoutInflater를 바로 가져올 수 없어서 부모의 context정보를 이용
        //main에 viewbinding과 하는짓 같음  layoutInflate를 바로 가져올 수 없어서 부모의 context정보 이용
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