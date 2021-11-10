package com.example.your_precioustime.SecondActivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.your_precioustime.databinding.BusitemLayoutBinding


class SubWayAdapter:ListAdapter<SubwayItem, SubWayAdapter.SubwayViewHolder>(diffUtil) {

    class SubwayViewHolder(val binding: BusitemLayoutBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(subwayItem: SubwayItem){
            binding.BusNumber.text = subwayItem.subwayid
            binding.waitBusNumber.text =subwayItem.waitsubwaystop

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubwayViewHolder {
        val view = BusitemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return SubwayViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubwayViewHolder, position: Int) {
       holder.bind(currentList[position])
    }

    companion object {

        val diffUtil= object : DiffUtil.ItemCallback<SubwayItem>(){
            override fun areItemsTheSame(oldItem: SubwayItem, newItem: SubwayItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: SubwayItem, newItem: SubwayItem): Boolean {
                return oldItem==newItem

            }

        }

    }


}