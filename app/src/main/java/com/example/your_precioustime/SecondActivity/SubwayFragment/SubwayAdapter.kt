package com.example.your_precioustime.SecondActivity.SubwayFragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.your_precioustime.Model.SubwayItem
import com.example.your_precioustime.R
import com.example.your_precioustime.databinding.SubwayItemBinding
import com.example.your_precioustime.ObjectManager.Myobject

@SuppressLint("StaticFieldLeak")
class SubwayAdapter : RecyclerView.Adapter<SubwayAdapter.MyViewHolder>() {

    lateinit var subwayItem: List<SubwayItem>
//    lateinit var hi:MutableList<String>


    inner class MyViewHolder(val binding: SubwayItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(subwayItem: SubwayItem) {
            val mytext = Myobject.myobject.changeSubwayText(subwayItem.arvlMsg2.toString())
            val trainLineNmtext = Myobject.myobject.changeSubwayResultText(subwayItem.trainLineNm.toString())


            binding.subwayitemSubwayId.text = subwayItem.subwayId
            binding.subwayitemTrainLineNm.text = trainLineNmtext
            binding.subwayitemBstatnNm.text = subwayItem.bstatnNm
            binding.subwayitemBarvlDt.text = mytext

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


            "경" -> {
                holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line_gyunge_background)

            }

            "공" -> {
                holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line_gonghang_background)

            }

            "경춘" -> {
                holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line_gyungchun_background)

            }


            "수" -> {
                holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line_bundang_background)

            }


            "신" -> {
                holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line_sinbundang_background)

            }

            "자" -> {
                holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line_jagibusang_background)

            }

            "우" -> {
                holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line_ui_background)

            }
        }

        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context , "되냐? : ${subwayItem.get(position).subwayId}",Toast.LENGTH_SHORT).show()
        }


//        holder.binding.saveBtn.setOnClickListener {
//            val subwayId = subwayItem[position].subwayId.toString()
//            val trainLineNm = subwayItem[position].trainLineNm
//            val bstatnNm = subwayItem[position].bstatnNm
//            val arvlMsg2 = subwayItem[position].arvlMsg2
//
//            val hi = SubwayEntity(null, subwayId, trainLineNm, bstatnNm, arvlMsg2)
//
//            subwayinsert(hi)
//
//            Toast.makeText(holder.itemView.context, "즐겨찾기에 등록 되었습니다.", Toast.LENGTH_SHORT).show()
//        }
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