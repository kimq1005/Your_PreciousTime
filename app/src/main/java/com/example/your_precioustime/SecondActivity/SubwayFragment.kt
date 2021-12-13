package com.example.your_precioustime.SecondActivity

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.your_precioustime.App
import com.example.your_precioustime.R
import com.example.your_precioustime.Retrofit.Retrofit_Client
import com.example.your_precioustime.Retrofit.Retrofit_InterFace
import com.example.your_precioustime.SecondActivity.DB.BusFavroiteDataBase
import com.example.your_precioustime.SecondActivity.DB.TestFavoriteModel
import com.example.your_precioustime.SecondActivity.DB.wowyeah
import com.example.your_precioustime.Url
import com.example.your_precioustime.Util.Companion.TAG
import com.example.your_precioustime.databinding.SubwayFragmentBinding

@SuppressLint("StaticFieldLeak")
class SubwayFragment:Fragment(R.layout.subway_fragment) {
    private var setbinding: SubwayFragmentBinding? = null
    private val binding get() = setbinding!!

    lateinit var busFavoriteDB : BusFavroiteDataBase
    lateinit var busfavoriteEntity: List<TestFavoriteModel>

    private var retrofitInterface: Retrofit_InterFace =
        Retrofit_Client.getClient(Url.BUS_MAIN_URL).create(Retrofit_InterFace::class.java)
    
    private var hellomyList= arrayListOf<wowyeah>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setbinding = SubwayFragmentBinding.bind(view)

        binding.clickhere.setOnClickListener {
            getAll()
        }

    }

    private fun getAll(){
        busFavoriteDB = BusFavroiteDataBase.getinstance(App.instance)!!

        val getAllTask = (object :AsyncTask<Unit,Unit,Unit>(){
            override fun doInBackground(vararg params: Unit?) {
                busfavoriteEntity = busFavoriteDB.busFavoriteDAO().busFavoriteGetAll()
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                val myList = arrayListOf<wowyeah>()
                val myFavoriteList = arrayListOf<TestFavoriteModel>()

                for(i in busfavoriteEntity.indices){
                    val hellomyList = arrayListOf<wowyeah>()


                    val stationName = busfavoriteEntity.get(i).stationName
                    val stationNodeNum = busfavoriteEntity.get(i).stationNodeNumber


                    val testfuck = TestFavoriteModel(
                        null,
                        null,
                        stationName,
                        stationNodeNum
                    )

                    myFavoriteList.add(testfuck)

                }

                Log.d(TAG, "onPostExecute: $myFavoriteList")

            }
        }).execute()

    }

    private fun setRecyclerView(citycode:String,stationName:String?)=with(binding){
        val call = retrofitInterface.StationNameGet(citycode,stationName)

        call.enqueue()
    }


}