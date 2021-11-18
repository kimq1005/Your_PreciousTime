package com.example.your_precioustime.SecondActivity

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.your_precioustime.Model.Bus
import com.example.your_precioustime.Model.Item
import com.example.your_precioustime.R
import com.example.your_precioustime.Util.Companion.TAG
import com.example.your_precioustime.databinding.BusitemLayoutBinding
import com.example.your_precioustime.databinding.MydialogBinding


class UpAdpater:RecyclerView.Adapter<UpAdpater.MyViewHolder>() {
    private var item: List<Item>? = null
    private val dialogbinding : MydialogBinding? = null
    private val binding: BusitemLayoutBinding? =null

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

        holder.itemView.setOnClickListener {
            binding?.DBArriveStationTextView?.text = ""
//            Toast.makeText(holder.itemView.context,"$position",Toast.LENGTH_SHORT).show()
//            val myDialog = MyDialog(holder.itemView.context)
//            myDialog.show()
//            myDialog.window?.setLayout(800,500)



//            if (dialogbinding != null) {
//                if(dialogbinding.YesBtn.onclick){
//                    Toast.makeText(holder.itemView.context,"되라좀",Toast.LENGTH_SHORT).show()
//
//
//                }
//            }

        }
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