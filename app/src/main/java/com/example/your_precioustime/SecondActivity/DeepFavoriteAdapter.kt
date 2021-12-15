package com.example.your_precioustime.SecondActivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.your_precioustime.Model.Item
import com.example.your_precioustime.Model.StationItem
import com.example.your_precioustime.SecondActivity.DB.TestFavoriteModel
import com.example.your_precioustime.databinding.BusitemLayoutBinding

class DeepFavoriteAdapter:ListAdapter<Item, DeepFavoriteAdapter.myViewHolder>(diffUtil) {
    inner class myViewHolder (var binding:BusitemLayoutBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(item:Item){
            binding.BusNumber.text = item.routeno
            binding.waitBusNumber.text = item.arrprevstationcnt.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view = BusitemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent, false)

        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
       holder.bind(currentList[position])
    }



    companion object {

        val diffUtil= object : DiffUtil.ItemCallback<Item>(){
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem==newItem

            }

        }

    }


}