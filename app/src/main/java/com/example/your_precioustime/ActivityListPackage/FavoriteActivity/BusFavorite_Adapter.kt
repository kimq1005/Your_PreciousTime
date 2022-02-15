package com.example.your_precioustime.ActivityListPackage.FavoriteActivity

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.your_precioustime.ActivityListPackage.FavoriteActivity.BusFavroite_DeepInfo.Bus_FavoriteDeepInfo_test
import com.example.your_precioustime.ActivityListPackage.FavoriteActivity.BusFavroite_DeepInfo.Bus_FavroiteDeepInfo_Activity
import com.example.your_precioustime.ObjectManager.Myobject
import com.example.your_precioustime.ObjectManager.citycodeSaveClass
import com.example.your_precioustime.SecondActivity.DB.OnDeleteInterFace
import com.example.your_precioustime.SecondActivity.DB.SubwayDB.TestFavoriteModel
import com.example.your_precioustime.databinding.ActivityBusFavroiteDeepInfoBinding

import com.example.your_precioustime.databinding.FavoritelistItemBinding


class BusFavorite_Adapter(var onDeleteInterFace: OnDeleteInterFace) :
    ListAdapter<TestFavoriteModel, BusFavorite_Adapter.SubwayViewHolder>(diffUtil) {

    lateinit var testFavoriteModel: List<TestFavoriteModel>
    private var busFavroiteDeepInfoBinding: ActivityBusFavroiteDeepInfoBinding? = null
    private val ff get() = busFavroiteDeepInfoBinding!!

    class SubwayViewHolder(val binding: FavoritelistItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(testFavoriteModel: TestFavoriteModel) {


            binding.FavoriteNameTextView.text = testFavoriteModel.stationName
            binding.FavoriteNodeIDTextView.text = testFavoriteModel.stationnodenode
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubwayViewHolder {
        val view =
            FavoritelistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SubwayViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubwayViewHolder, position: Int) {
        val favoriteModelList = currentList[position]

        holder.bind(currentList[position])
        val favoriteStationName = favoriteModelList.stationName
        val favoritenodenum = favoriteModelList.stationNodeNumber
        val citycode = favoriteModelList.citycode


        holder.itemView.setOnClickListener {
            citycodeSaveClass.citycodeSaveClass.Savecitycode("favroitebuscitycode",citycode.toString())

            val intent = Intent(holder.itemView.context, Bus_FavroiteDeepInfo_Activity::class.java).apply {
                putExtra("citycode",citycode)
                putExtra("favoriteStationName", favoriteStationName)
                putExtra("favoritenodenum", favoritenodenum)
            }

            holder.itemView.context.startActivity(intent)
        }

        holder.binding.stardeletebtn.setOnClickListener {
            onDeleteInterFace.onDeleteFavroitelist(favoriteModelList)
            Toast.makeText(holder.itemView.context, "즐겨찾기에서 삭제 되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }


    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<TestFavoriteModel>() {
            override fun areItemsTheSame(
                oldItem: TestFavoriteModel,
                newItem: TestFavoriteModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: TestFavoriteModel,
                newItem: TestFavoriteModel
            ): Boolean {
                return oldItem == newItem

            }

        }

    }


}