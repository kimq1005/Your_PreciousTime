package com.example.your_precioustime.SecondActivity.SubwayFragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.your_precioustime.Model.SubwayItem
import com.example.your_precioustime.R
import com.example.your_precioustime.databinding.SubwayItemBinding

class SubwayAdapter : RecyclerView.Adapter<SubwayAdapter.MyViewHolder>() {

    lateinit var subwayItem: List<SubwayItem>

    inner class MyViewHolder(val binding: SubwayItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(subwayItem: SubwayItem) {
            binding.subwayitemSubwayId.text= subwayItem.subwayId

            binding.subwayitemTrainLineNm.text = subwayItem.trainLineNm
            binding.subwayitemBstatnNm.text = subwayItem.bstatnNm
            binding.subwayitemBarvlDt.text = subwayItem.barvlDt.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = SubwayItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(subwayItem[position])


        for(i in subwayItem.indices){
            when(subwayItem.get(i).subwayId){
                "1001"->{
                     holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line1_background)
                }

                "1002"->{
                    holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line2_background)
                }

                "1003"->{
                    holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line3_background)
                }

                "4"->{
                    holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line4_background)
                }

                "5"->{
                    holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line5_background)
                }

                "6"->{
                    holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line6_background)
                }

                "7"->{
                    holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line7_background)
                }

                "8"->{
                    holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line8_background)
                }

                "9"->{
                    holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line9_background)
                }


                "1063"->{
                    holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line_gyunge_background)
                }

                "1067"->{
                    holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line_gyungchun_background)
                }

                "1075"->{
                    holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line_bundang_background)
                }

                "1077"->{
                    holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line_sinbundang_background)
                }

                "1091"->{
                    holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line_jagibusang_background)
                }

                "1092"->{
                    holder.binding.subwayitemSubwayId.setBackgroundResource(R.drawable.line_ui_background)
                }
            }
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