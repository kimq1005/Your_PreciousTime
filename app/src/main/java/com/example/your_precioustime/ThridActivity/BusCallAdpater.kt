package com.example.your_precioustime.ThridActivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.your_precioustime.SecondActivity.DB.BUSEntity
import com.example.your_precioustime.databinding.FavoritesbusitemLayoutBinding

class BusCallAdpater:ListAdapter<BUSEntity, BusCallAdpater.BusCallViewHolder>(diffUtil) {

    inner class BusCallViewHolder(val binding : FavoritesbusitemLayoutBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(busEntity: BUSEntity)= with(binding){
            FVBusNum.text = busEntity.busNumber
            FVWaitStaion.text = busEntity.arriveStation
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusCallViewHolder {

        val view = FavoritesbusitemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return BusCallViewHolder(view)

    }

    override fun onBindViewHolder(holder: BusCallViewHolder, position: Int) {
       holder.bind(currentList[position])
    }



    companion object {

        val diffUtil= object : DiffUtil.ItemCallback<BUSEntity>(){
            override fun areItemsTheSame(oldItem: BUSEntity, newItem: BUSEntity): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: BUSEntity, newItem: BUSEntity): Boolean {
                return oldItem==newItem
            }


        }

    }



}