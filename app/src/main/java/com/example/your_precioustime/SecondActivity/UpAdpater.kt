package com.example.your_precioustime.SecondActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.your_precioustime.R
import kotlinx.android.synthetic.main.bus_fragment.view.*
import kotlinx.android.synthetic.main.busitem_layout.view.*

class UpAdpater:ListAdapter<BusItem, UpAdpater.MyViewHolder>(diffUtill) {

    inner class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        fun bind(busItem: BusItem){
            itemView.Bus_Number.text = busItem.busid
            itemView.waitBusNumber.text = busItem.waitbusstop
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.busitem_layout, parent,false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        return holder.bind(currentList[position])
    }


    companion object{
        val diffUtill = object : DiffUtil.ItemCallback<BusItem>(){
            override fun areItemsTheSame(oldItem: BusItem, newItem: BusItem): Boolean {
               return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: BusItem, newItem: BusItem): Boolean {
                return oldItem == newItem
            }

        }
    }
}