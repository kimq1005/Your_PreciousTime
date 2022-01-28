package com.example.your_precioustime.SecondActivity.Busfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.your_precioustime.Model.StationBus
import com.example.your_precioustime.ObjectManager.Myobject
import com.example.your_precioustime.Retrofit.Retrofit_Client
import com.example.your_precioustime.Retrofit.Retrofit_InterFace
import com.example.your_precioustime.Url
import com.example.your_precioustime.Util
import com.example.your_precioustime.databinding.ActivityBusBinding
import retrofit2.Call
import retrofit2.Response

class Bus_Activity : AppCompatActivity() {

    private var busBinding: ActivityBusBinding? = null
    private val binding get() = busBinding!!
    private var isFabOpen = false

    lateinit var busStationSearchAdapter: Bus_Station_Search_Adapter

    private var retrofitInterface: Retrofit_InterFace =
        Retrofit_Client.getClient(Url.BUS_MAIN_URL).create(Retrofit_InterFace::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        busBinding = ActivityBusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val suwoncitycode: String = "31010"
        val citycode :String = intent.getStringExtra("citycode").toString()
        val StationEditName = binding.SearchEditText2.text.toString()
        SetRecyclerView(citycode, null)
        Myobject.myobject.ToggleSet(
            this,
            binding.floatingBtn,
            binding.FavroiteFloatBtn,
            binding.SubwayFloatBtn,
            binding.BusfloatBtn
        )
        ClickSearchBtn()


    }

    private fun ClickSearchBtn() = with(binding) {

        clickhere.setOnClickListener {
            val suwoncitycode: String = "31010"
            val citycode :String = intent.getStringExtra("citycode").toString()
            val StationEditName = SearchEditText2.text.toString()
            SetRecyclerView(citycode, StationEditName)
//            hellomy(suwoncitycode,"GGB203000129")
        }

        SearchEditText.setOnClickListener {
            noResultTextView.visibility = View.INVISIBLE
        }

    }


    fun SetRecyclerView(citycode: String, stationName: String?) = with(binding) {

        val stationcalls = retrofitInterface.StationNameGet(
            cityCode = citycode,
            staionName = stationName,
            null
        )

        stationcalls.enqueue(object : retrofit2.Callback<StationBus> {

            override fun onResponse(call: Call<StationBus>, response: Response<StationBus>) {
                val body = response.body()
                busStationSearchAdapter = Bus_Station_Search_Adapter()

                body?.let { it ->
                    val hello = body.body.items.item
                    busRecyclerView.apply {
                        adapter = busStationSearchAdapter
                        layoutManager = LinearLayoutManager(context)
                        busStationSearchAdapter.submitList(hello)
                    }
                }
            }

            override fun onFailure(call: Call<StationBus>, t: Throwable) {
                Log.d(Util.TAG, "onFailure:$t")
                noResultTextView.visibility = View.VISIBLE

            }

        })


    }

}