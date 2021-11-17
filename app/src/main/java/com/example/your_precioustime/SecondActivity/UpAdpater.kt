package com.example.your_precioustime.SecondActivity

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.your_precioustime.Model.Bus
import com.example.your_precioustime.Model.Item
import com.example.your_precioustime.R
import com.example.your_precioustime.databinding.BusitemLayoutBinding


class UpAdpater:RecyclerView.Adapter<UpAdpater.MyViewHolder>() {
    private var item: List<Item>? = null

    class MyViewHolder(val binding:BusitemLayoutBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: Item){
            binding.BusNumber.text =item.routeno.toString()
            binding.waitBusNumber.text = item.arrprevstationcnt.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = BusitemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        item?.get(position)?.let { holder.bind(it) }
    }



    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list:List<Item>){
        item = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return item?.size!!
    }


}