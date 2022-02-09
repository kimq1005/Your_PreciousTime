package com.example.your_precioustime.ActivityListPackage.FavoriteActivity.BusFavroite_DeepInfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.your_precioustime.Model.Item
import com.example.your_precioustime.databinding.BusitemLayoutBinding

class Bus_DeepFavoriteAdapter:ListAdapter<Item, Bus_DeepFavoriteAdapter.myViewHolder>(diffUtil) {

    inner class myViewHolder (var binding:BusitemLayoutBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:Item){
            val mytime = item.arrtime!!
//            Log.d(Util.TAG, "bind: $mytime")
            val second = mytime/60
//            Log.d(Util.TAG, "bind: $second")
            binding.BusNumber.text = item.routeno
            binding.waitBusNumber.text = item.arrprevstationcnt.toString()
            binding.waitTime.text = second.toString()
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