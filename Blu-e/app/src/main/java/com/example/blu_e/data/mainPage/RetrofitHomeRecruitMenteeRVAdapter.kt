package com.example.blu_e.data.mainPage

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blu_e.databinding.RecyclerviewMenteeCardBinding
import com.example.blu_e.mentoring.RequestMentoringActivity

class RetrofitHomeRecruitMenteeRVAdapter(private val items: ArrayList<FindHotMenteeItem> = arrayListOf(), private val context: Context) :
    RecyclerView.Adapter<RetrofitHomeRecruitMenteeRVAdapter.ViewHolder>() {
    var pickId = 0
    //각 항목에 필요한 기능 구현, ViewHolder 반환
    inner class ViewHolder(private val viewBinding: RecyclerviewMenteeCardBinding):
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(data: FindHotMenteeItem) {
            viewBinding.menteeCardTitle.text = data.title
            viewBinding.menteeCardDesiredSubject.text = data.subject
            viewBinding.menteeCardDesiredStartPeriod.text = data.period
            viewBinding.menteeCardMethod.text = data.mentoringMethod
            viewBinding.menteeCardArea.text = data.wishGender
            pickId = data.pickId
        }
    }

    //뷰홀더 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetrofitHomeRecruitMenteeRVAdapter.ViewHolder {
        val viewBinding = RecyclerviewMenteeCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(viewBinding)
    }

    //항목 뷰에 데이터 연결
    override fun onBindViewHolder(holder: RetrofitHomeRecruitMenteeRVAdapter.ViewHolder, position: Int) {
        val model = items!![position]
        holder.bind(model)
        holder.itemView.setOnClickListener {
            Log.d("selected", position.toString())
            Log.d("pickId", items!![position].pickId.toString())
            var intent: Intent = Intent(context, RequestMentoringActivity::class.java)
            intent.putExtra("pickId", items!![position].pickId)
            context.startActivity(intent)
//            itemClickListener.onClick(it, position, pickId)
        }
    }

    //아이템 개수
    override fun getItemCount(): Int = items.size

    interface ItemClickListener {
        fun onClick(view: View, position: Int, pickId: Int)
    }

    private lateinit var itemClickListener: ItemClickListener
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}
