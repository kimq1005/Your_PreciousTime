package com.example.your_precioustime.SecondActivity

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.your_precioustime.Model.StationItem
import com.example.your_precioustime.databinding.BusStationSearchitemLayoutBinding

class Bus_Station_Search_Adapter:RecyclerView.Adapter<Bus_Station_Search_Adapter.MyViewHolder>() {
    private var stationItem:List<StationItem>? = null

    inner class MyViewHolder(val binding: BusStationSearchitemLayoutBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(stationItem: StationItem){
            binding.StationNameTextView.text = stationItem.nodenm
            binding.StationNodeIDTextView.text = stationItem.nodeno
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = BusStationSearchitemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        stationItem?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return stationItem?.size!!
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list:List<StationItem>){
        stationItem= list
        notifyDataSetChanged()
    }
}