package com.example.your_precioustime.SecondActivity.SubwayFragment

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Database
import com.example.your_precioustime.App
import com.example.your_precioustime.Model.SubwayItem
import com.example.your_precioustime.R
import com.example.your_precioustime.SecondActivity.DB.SubwayDB.SubwayDataBase
import com.example.your_precioustime.SecondActivity.DB.SubwayDB.SubwayEntity
import com.example.your_precioustime.Util.Companion.TAG
import com.example.your_precioustime.databinding.SubwayItemBinding

@SuppressLint("StaticFieldLeak")
class SubwayAdapter : RecyclerView.Adapter<SubwayAdapter.MyViewHolder>() {

    lateinit var subwayItem: List<SubwayItem>
    lateinit var subwayDataBase: SubwayDataBase




    inner class MyViewHolder(val binding: SubwayItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(subwayItem: SubwayItem) {
            binding.subwayitemSubwayId.text = subwayItem.subwayId
            binding.subwayitemTrainLineNm.text = subwayItem.trainLineNm
            binding.subwayitemBstatnNm.text = subwayItem.bstatnNm
            binding.subwayitemBarvlDt.text = subwayItem.arvlMsg2
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = SubwayItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(subwayItem[position])

        when (subwayItem[position].subwayId) {
            "1" -> {
                holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line1_background)
            }

            "2" -> {
                holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line2_background)
            }

            "3" -> {
                holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line3_background)
            }

            "4" -> {
                holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line4_background)
            }

            "5" -> {
                holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line5_background)
            }

            "6" -> {
                holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line6_background)
            }

            "7" -> {
                holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line7_background)
            }

            "8" -> {
                holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line8_background)
            }

            "9" -> {
                holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line9_background)
            }


            "경의" -> {
                holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line_gyunge_background)
            }

            "공항" -> {
                holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line_gonghang_background)
            }

            "경춘" -> {
                holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line_gyungchun_background)
            }


            "수인" -> {
                holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line_bundang_background)
            }


            "신" -> {
                holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line_sinbundang_background)
            }

            "자기" -> {
                holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line_jagibusang_background)
            }

            "우이" -> {
                holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line_ui_background)
            }
        }

        holder.binding.saveBtn.setOnClickListener {
            val subwayId = subwayItem[position].subwayId.toString()
            val trainLineNm = subwayItem[position].trainLineNm
            val bstatnNm = subwayItem[position].bstatnNm
            val arvlMsg2 = subwayItem[position].arvlMsg2

            val testmodel = mutableListOf<SubwayItem>()

            testmodel.add(
                SubwayItem(subwayId, trainLineNm, bstatnNm, arvlMsg2)
            )


        }
    }


    override fun getItemCount(): Int {
        return subwayItem.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<SubwayItem>) {
        subwayItem = list
        notifyDataSetChanged()
    }




}