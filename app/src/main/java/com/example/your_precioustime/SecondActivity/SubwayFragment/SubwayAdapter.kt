package com.example.your_precioustime.SecondActivity.SubwayFragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.your_precioustime.Model.SubwayItem
import com.example.your_precioustime.databinding.SubwayItemBinding

class SubwayAdapter:RecyclerView.Adapter<SubwayAdapter.MyViewHolder>() {
    lateinit var subwayItem: List<SubwayItem>
    inner class MyViewHolder(val binding:SubwayItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(subwayItem:SubwayItem){
            binding.subwayitemTrainLineNm.text = subwayItem.trainLineNm
            binding.subwayitemBstatnNm.text = subwayItem.bstatnNm
            binding.subwayitemBarvlDt.text = subwayItem.barvlDt
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = SubwayItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(subwayItem[position])
    }

    override fun getItemCount(): Int {
        return subwayItem.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list:List<SubwayItem>){
        subwayItem = list
        notifyDataSetChanged()
    }
}