package com.example.your_precioustime.ActivityListPackage.BusActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.your_precioustime.DB.BusFavroiteDataBase
import com.example.your_precioustime.Model.Bus
import com.example.your_precioustime.Model.Item
import com.example.your_precioustime.ObjectManager.citycodeSaveClass
import com.example.your_precioustime.Retrofit.Retrofit_Client
import com.example.your_precioustime.Retrofit.Retrofit_InterFace
import com.example.your_precioustime.SecondActivity.DB.SubwayDB.TestFavoriteModel
import com.example.your_precioustime.Url
import com.example.your_precioustime.Util
import com.example.your_precioustime.databinding.ActivityBusStationInfoBinding
import retrofit2.Call
import retrofit2.Response

class Bus_StationInfo_Activity : AppCompatActivity() {

    private var busStationinfoBinding: ActivityBusStationInfoBinding? = null
    private val binding get() = busStationinfoBinding!!

    private lateinit var busMaps_Adapater: BusMaps_Adpater
    lateinit var busFavoriteDB: BusFavroiteDataBase
    lateinit var activitybusfavoriteEntity: List<TestFavoriteModel>

    private var retrofitInterface: Retrofit_InterFace =
        Retrofit_Client.getClient(Url.BUS_MAIN_URL).create(Retrofit_InterFace::class.java)

    lateinit var busStationSearchAdapter: Bus_Station_Search_Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        busStationinfoBinding = ActivityBusStationInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backbtn.setOnClickListener {
            onBackPressed()
            finish()
        }

        SetBusStationRecyclerView()


    }


    private fun SetBusStationRecyclerView() = with(binding) {

        val stationName = intent.getStringExtra("stationName").toString()
        binding.BusInfoTitleTextView.text = stationName

        val stationNodeNumber = intent.getStringExtra("stationNodeNumber").toString()
        val citycode = citycodeSaveClass.citycodeSaveClass.Loadcitycode("citycode", "citycode")
//        val citycode: String = "31010"

        val call = retrofitInterface.BusGet(citycode, stationNodeNumber)
//        val call = retrofitInterface.BusGet("25","DJB8001793")
        call.enqueue(object : retrofit2.Callback<Bus> {
            override fun onResponse(call: Call<Bus>, response: Response<Bus>) {
                busMaps_Adapater = BusMaps_Adpater()

                val body = response.body()

                body?.let {
                    val hello = body.body.items.item

                    val hi = mutableListOf<Item>()

                    for (i in hello.indices) {
                        val busNm: String
                        val waitbus: Int
                        val waittime: Int

                        busNm = hello.get(i).routeno!!
                        waitbus = hello.get(i).arrprevstationcnt!!
                        waittime = hello.get(i).arrtime!!

                        hi.add(
                            Item(
                                busNm, waitbus, waittime
                            )
                        )

                    }
                    Log.d(Util.TAG, "\n 전체값 리스트 : $hi \n")


                    val firstList = hi.filterIndexed { index, i ->

                        index % 2 == 0
                    }


                    val secondList = hi.filterIndexed { index, item ->
                        index % 2 == 1
                    }


                    val ResultList = mutableListOf<Item>()
                    val SecondResultList = mutableListOf<Item>()


                    firstList.forEach {
                        val ARouteNo = it.routeno
                        val AWaitstation = it.arrprevstationcnt
                        val AWaitTime = it.arrtime


                        secondList.forEach {
                            val BRouteNo = it.routeno
                            val BWaitstation = it.arrprevstationcnt

                            if (ARouteNo == BRouteNo) {
                                if (AWaitstation!! > BWaitstation!!) {
                                    ResultList.add(
                                        Item(
                                            it.routeno,
                                            it.arrprevstationcnt,
                                            it.arrtime
                                        )
                                    )

                                } else {
                                    ResultList.add(Item(ARouteNo, AWaitstation, AWaitTime))
                                }
                            }

                        }

                        BusStationInfoRecyclerView.apply {
                            adapter = busMaps_Adapater
                            layoutManager = LinearLayoutManager(context)
                            busMaps_Adapater.submitList(ResultList)
                        }


                    }

                }

            }

            override fun onFailure(call: Call<Bus>, t: Throwable) {
                Log.d(Util.TAG, "오류: $t")
            }

        })


    }
}