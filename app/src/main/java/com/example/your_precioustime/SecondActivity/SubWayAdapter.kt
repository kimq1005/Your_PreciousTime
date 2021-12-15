package com.example.your_precioustime.SecondActivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.your_precioustime.Model.StationItem
import com.example.your_precioustime.SecondActivity.DB.TestFavoriteModel
import com.example.your_precioustime.databinding.BusStationSearchitemLayoutBinding



class SubWayAdapter:ListAdapter<TestFavoriteModel,SubWayAdapter.SubwayViewHolder>(diffUtil) {

    class SubwayViewHolder(val binding: BusStationSearchitemLayoutBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(testFavoriteModel: TestFavoriteModel){
            binding.StationNameTextView.text = testFavoriteModel.stationName
            binding.StationNodeIDTextView.text =testFavoriteModel.stationNodeNumber

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubwayViewHolder {
        val view = BusStationSearchitemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SubwayViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubwayViewHolder, position: Int) {
       holder.bind(currentList[position])
    }

    companion object {

        val diffUtil= object : DiffUtil.ItemCallback<TestFavoriteModel>(){
            override fun areItemsTheSame(oldItem: TestFavoriteModel, newItem: TestFavoriteModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: TestFavoriteModel, newItem: TestFavoriteModel): Boolean {
                return oldItem==newItem

            }

        }

    }


}