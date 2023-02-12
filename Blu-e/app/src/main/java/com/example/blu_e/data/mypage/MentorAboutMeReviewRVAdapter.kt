package com.example.blu_e.data.mypage

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import android.util.Log
import android.util.MalformedJsonException
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.blu_e.MainActivity
import com.example.blu_e.databinding.ItemAboutMeReviewBinding
import com.google.gson.internal.bind.TypeAdapters.URL
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.URL

class MentorAboutMeReviewRVAdapter(private  val dataList: ArrayList<MentorReviewListData> = arrayListOf()): RecyclerView.Adapter<MentorAboutMeReviewRVAdapter.DataViewHolder>() {
    inner class DataViewHolder(private  val viewBinding: ItemAboutMeReviewBinding): RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(data: MentorReviewListData){
            viewBinding.nicknameMenteeReviewList.text = data.nickname
            viewBinding.menteeReviewText.text = data.contents

            /*Picasso.get()
                .load(data.userImg)
                .into(viewBinding.imageMenteeReviewList)
            Log.d("정보 수정 이미지", "피카소 성공")*/
            /*CoroutineScope(Dispatchers.Main).launch {
                val bitmap = withContext(Dispatchers.IO){
                    ImageLoader.loadImage(data.userImg)
                }
                viewBinding.imageMenteeReviewList.setImageBitmap(bitmap)
                Log.d("리뷰목록 이미지", "비트맵 성공")
            }*/
            /*Glide.with(viewBinding.imageMenteeReviewList)
                .load(data.userImg)
                .into(viewBinding.imageMenteeReviewList)
            Log.d("리뷰목록 이미지", "비트맵 성공")*/
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MentorAboutMeReviewRVAdapter.DataViewHolder {
        val viewBinding = ItemAboutMeReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: MentorAboutMeReviewRVAdapter.DataViewHolder, position: Int) {
        holder.bind(dataList[position])

    }

    override fun getItemCount(): Int = dataList.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    /*object ImageLoader{
        suspend fun loadImage(imageUrl:String):Bitmap?{
            val bmp: Bitmap?=null
            try{
                val url = URL(imageUrl)
                val stream = url.openStream()

                return BitmapFactory.decodeStream(stream)
            } catch (e: MalformedJsonException){
                e.printStackTrace()
            }catch (e:IOException){
                e.printStackTrace()
            }
            return bmp
        }
    }*/
}