package com.example.your_precioustime.SecondActivity

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.your_precioustime.R
import com.example.your_precioustime.databinding.BusitemLayoutBinding


class UpAdpater:RecyclerView.Adapter<UpAdpater.MyViewHolder>() {
    lateinit var busItem: List<BusItem>

    class MyViewHolder(val binding:BusitemLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(busItem: BusItem){
            binding.BusNumber.text =busItem.busid
            binding.waitBusNumber.text = busItem.waitbusstop
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = BusitemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(busItem[position])
    }

    override fun getItemCount(): Int {
       return busItem.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list:List<BusItem>){
        busItem = list
        notifyDataSetChanged()
    }


}