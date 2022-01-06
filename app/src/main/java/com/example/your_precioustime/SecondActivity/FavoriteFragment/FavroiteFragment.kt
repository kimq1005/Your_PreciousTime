package com.example.your_precioustime.SecondActivity.FavoriteFragment

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.your_precioustime.App
import com.example.your_precioustime.R
import com.example.your_precioustime.SecondActivity.DB.BusFavroiteDataBase
import com.example.your_precioustime.SecondActivity.DB.OnDeleteInterFace
import com.example.your_precioustime.SecondActivity.DB.SubwayDB.TestFavoriteModel
import com.example.your_precioustime.SecondActivity.SubwayFragment.SubwayAdapter
import com.example.your_precioustime.Util.Companion.TAG
import com.example.your_precioustime.databinding.FavoritelistFragmentBinding

@SuppressLint("StaticFieldLeak")
class FavroiteFragment:Fragment(R.layout.favoritelist_fragment), OnDeleteInterFace {
    private var setbinding: FavoritelistFragmentBinding? = null
    private val binding get() = setbinding!!

    lateinit var busFavoriteDB : BusFavroiteDataBase
    lateinit var busfavoriteEntity: List<TestFavoriteModel>

    lateinit var subwayAdapter: FavoriteAdapter
    lateinit var testAdapter: SubwayAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setbinding = FavoritelistFragmentBinding.bind(view)
        busFavoriteDB = BusFavroiteDataBase.getinstance(App.instance)!!

        getAll()
        testAdapter = SubwayAdapter()



    }

    private fun getAll(){

        val getAllTask = (object :AsyncTask<Unit,Unit,Unit>(){
            override fun doInBackground(vararg params: Unit?) {
                busfavoriteEntity = busFavoriteDB.busFavoriteDAO().busFavoriteGetAll()
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)

                Log.d(TAG, "onPostExecute: $busfavoriteEntity")
                setRecyclerViewfuck()

            }
        }).execute()
    }



    private fun setRecyclerViewfuck()=with(binding){

        subwayAdapter = FavoriteAdapter(this@FavroiteFragment)

        favroitePageBusRecyclerView.apply {
            adapter = subwayAdapter
//            layoutManager= LinearLayoutManager(context)
            layoutManager= GridLayoutManager(App.instance,2,GridLayoutManager.VERTICAL,false)
            subwayAdapter.submitList(busfavoriteEntity)

        }

    }


    private fun ondeleteList(testFavoriteModel: TestFavoriteModel){

        val deleteTask = (object :AsyncTask<Unit,Unit,Unit>(){
            override fun doInBackground(vararg params: Unit?) {
                busFavoriteDB.busFavoriteDAO().busFavoriteDelete(testFavoriteModel)
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                getAll()
            }

        }).execute()
    }

    override fun onDeleteFavroitelist(testFavoriteModel: TestFavoriteModel) {
        ondeleteList(testFavoriteModel)
    }


//    private fun setFavoriteBusStation(citycode:String,stationName:String?)=with(binding){
//
//        val call = retrofitInterface.StationNameGet(citycode,stationName)
//
//        call.enqueue(object :retrofit2.Callback<StationBus>{
//            override fun onResponse(call: Call<StationBus>, response: Response<StationBus>) {
//                val body = response.body()
//
//                subwayAdapter= SubWayAdapter()
//
//                body?.let{it->
//                    val hello = body.body.items.item
//
//                    subwayRecyclerView.apply {
//                        adapter = subwayAdapter
//                        layoutManager = LinearLayoutManager(context)
//                        subwayAdapter.submitList()
//                    }
//
//                }
//            }
//
//            override fun onFailure(call: Call<StationBus>, t: Throwable) {
//
//            }
//        })
//
//    }




}