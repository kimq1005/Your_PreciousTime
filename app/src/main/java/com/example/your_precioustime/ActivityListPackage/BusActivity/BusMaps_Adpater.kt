package com.example.your_precioustime.ActivityListPackage.BusActivity

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.your_precioustime.App
import com.example.your_precioustime.DB.BUSStationNameDataBase
import com.example.your_precioustime.Model.Item
import com.example.your_precioustime.SecondActivity.DB.BUSStationNameEntity
import com.example.your_precioustime.Util.Companion.TAG
import com.example.your_precioustime.databinding.BusitemLayoutBinding



@SuppressLint("StaticFieldLeak")
class BusMaps_Adpater : RecyclerView.Adapter<BusMaps_Adpater.MyViewHolder>() {

    private var item: List<Item>? = null

    lateinit var busstationnameDB: BUSStationNameDataBase
    lateinit var busstationnameEntity: List<BUSStationNameEntity>


    class MyViewHolder(val binding: BusitemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(item: Item) {

            val mytime = item.arrtime!!
            val second = mytime / 60
            binding.BusNumber.text = item.routeno.toString()
            binding.waitBusNumber.text = item.arrprevstationcnt.toString()
            binding.waitTime.text = second.toString()

        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = BusitemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        item?.get(position)?.let {
            holder.bind(it)
        }


    }


    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<Item>) {
        item = list
        notifyDataSetChanged()
    }



    override fun getItemCount(): Int {
        return item?.size!!
    }


    private fun BUSnumInsert(busStationNameEntity: BUSStationNameEntity) {

        busstationnameDB = BUSStationNameDataBase.getinstance(App.instance)!!


        var businsertTask = (object : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                busstationnameDB.busstationnameDao().busStationNameInsert(busStationNameEntity)
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                busstationnameGetAll()
            }
        }).execute()

    }

    private fun busstationnameGetAll() {
        val busGetAllTask = (object : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                busstationnameEntity = busstationnameDB.busstationnameDao().busStationNameGetAll()
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)

                Log.d(TAG, "onPostExecute: $busstationnameEntity")
            }
        }).execute()
    }


}