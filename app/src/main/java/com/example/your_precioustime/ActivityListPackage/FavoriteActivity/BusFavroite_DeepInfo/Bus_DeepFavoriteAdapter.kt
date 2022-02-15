package com.example.your_precioustime.ActivityListPackage.FavoriteActivity.BusFavroite_DeepInfo

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.your_precioustime.App
import com.example.your_precioustime.DB.BUSStationNameDataBase
import com.example.your_precioustime.DB.BusFavroiteDataBase
import com.example.your_precioustime.Model.Item
import com.example.your_precioustime.ObjectManager.citycodeCallObject
import com.example.your_precioustime.ObjectManager.citycodeSaveClass
import com.example.your_precioustime.R
import com.example.your_precioustime.SecondActivity.DB.SubwayDB.TestFavoriteModel
import com.example.your_precioustime.Util.Companion.TAG
import com.example.your_precioustime.databinding.ActivityBusFavroiteDeepInfoBinding
import com.example.your_precioustime.databinding.BusitemLayoutBinding

@SuppressLint("StaticFieldLeak")
class Bus_DeepFavoriteAdapter : ListAdapter<Item, Bus_DeepFavoriteAdapter.myViewHolder>(diffUtil) {

    lateinit var activitybusfavoriteEntity: List<TestFavoriteModel>
    lateinit var busFavoriteDB: BusFavroiteDataBase


    inner class myViewHolder(var binding: BusitemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            val mytime = item.arrtime!!
            val second = mytime / 60
            val citycode = citycodeSaveClass.citycodeSaveClass.Loadcitycode(
                "favroitebuscitycode",
                "favroitebuscitycode"
            )
            val cityname = citycodeCallObject.citycodeCallObject.returncitynamecode(citycode)


            binding.BusNumber.text = item.routeno
            binding.waitBusNumber.text = item.arrprevstationcnt.toString()
            binding.waitTime.text = second.toString()
            binding.BusCityname.text = cityname

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view = BusitemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.bind(currentList[position])

    }


    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem

            }
        }

    }


}