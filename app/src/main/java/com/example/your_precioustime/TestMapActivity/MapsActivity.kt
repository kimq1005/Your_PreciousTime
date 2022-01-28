package com.example.your_precioustime.TestMapActivity

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.your_precioustime.App
import com.example.your_precioustime.Model.Bus
import com.example.your_precioustime.Model.Item
import com.example.your_precioustime.Model.StationBus
import com.example.your_precioustime.ObjectManager.citycodeSaveClass
import com.example.your_precioustime.R
import com.example.your_precioustime.Retrofit.Retrofit_Client
import com.example.your_precioustime.Retrofit.Retrofit_InterFace
import com.example.your_precioustime.SecondActivity.Busfragment.Bus_Station_Search_Adapter
import com.example.your_precioustime.SecondActivity.DB.BusFavroiteDataBase
import com.example.your_precioustime.SecondActivity.DB.SubwayDB.TestFavoriteModel
import com.example.your_precioustime.SecondActivity.FavoriteFragment.UpAdpater
import com.example.your_precioustime.Url
import com.example.your_precioustime.Util
import com.example.your_precioustime.Util.Companion.TAG

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.example.your_precioustime.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.activity_real_main_list.*
import retrofit2.Call
import retrofit2.Response
import kotlin.math.log

@SuppressLint("StaticFieldLeak")
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    lateinit var testfuck: MutableList<StationBus>
    private var mymarker: Marker? = null
    private lateinit var upAdpater: UpAdpater
    lateinit var busFavoriteDB: BusFavroiteDataBase
    lateinit var activitybusfavoriteEntity: List<TestFavoriteModel>


    private var retrofitInterface: Retrofit_InterFace =
        Retrofit_Client.getClient(Url.BUS_MAIN_URL).create(Retrofit_InterFace::class.java)

    lateinit var busStationSearchAdapter: Bus_Station_Search_Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        busFavoriteDB = BusFavroiteDataBase.getinstance(App.instance)!!

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backwowbtn.setOnClickListener {
            onBackPressed()
            finish()
        }


        setMap()
        SetBusStationRecyclerView()
        busFavoriteGetAll()
        savemystation()
        SetmapView()


    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        //마커가즈아

        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        //일단 제가 원하는건 api로 설정해서 내가 검색한 정류장에대한 위치가 지도에 표시가 되어야해요
    }

    private fun setMap() {

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    fun SetmapView() = with(binding) {
        val stationname = intent.getStringExtra("stationName")
        val stationnodenode = intent.getStringExtra("stationnodenode")
        Log.d(TAG, "SetmapView: $stationname , $stationnodenode")

        val citycode = citycodeSaveClass.citycodeSaveClass.Loadcitycode("citycode","citycode")

        val stationcalls = retrofitInterface.StationNameGet(
            cityCode = citycode,
            staionName = null,
            nodeNo = stationnodenode
        )

        stationcalls.enqueue(object : retrofit2.Callback<StationBus> {

            override fun onResponse(call: Call<StationBus>, response: Response<StationBus>) {
                val body = response.body()
                upAdpater = UpAdpater()

                val myLocationlatlng = LatLngBounds.Builder()
                Log.d(TAG, "setMapView: $body")
                busStationSearchAdapter = Bus_Station_Search_Adapter()

                body?.let { it ->
                    val hello = body.body.items.item

                    for (i in hello.indices) {
                        val xLocation = hello.get(i).gpslati?.toDouble()!!
                        val yLocation = hello.get(i).gpslong?.toDouble()!!
                        val mapStationname = hello.get(i).nodenm?.toString()!!
                        val position = LatLng(xLocation, yLocation)

                        val marker = MarkerOptions().position(position).title(mapStationname)
                        mMap.addMarker(marker)
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 18f))
                        myLocationlatlng.include(position)

                    }

                }
            }

            override fun onFailure(call: Call<StationBus>, t: Throwable) {
                Log.d(Util.TAG, "onFailure:$t")

            }

        })


    }


    private fun SetBusStationRecyclerView() = with(binding) {

        val stationName = intent.getStringExtra("stationName").toString()
        binding.BusStationName.text = stationName

        val stationNodeNumber = intent.getStringExtra("stationNodeNumber").toString()
        val citycode = citycodeSaveClass.citycodeSaveClass.Loadcitycode("citycode","citycode")
//        val citycode: String = "31010"

        val call = retrofitInterface.BusGet(citycode, stationNodeNumber)
//        val call = retrofitInterface.BusGet("25","DJB8001793")
        call.enqueue(object : retrofit2.Callback<Bus> {
            override fun onResponse(call: Call<Bus>, response: Response<Bus>) {
                upAdpater = UpAdpater()

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
                    Log.d(TAG, "\n 전체값 리스트 : $hi \n")


                    val firstList = hi.filterIndexed { index, i ->

                        index % 2 == 0
                    }


                    val secondList = hi.filterIndexed { index, item ->
                        index % 2 == 1
                    }


                    val ResultList = mutableListOf<Item>()


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

                        busreclerView.apply {
                            adapter = upAdpater
                            layoutManager = LinearLayoutManager(context)
                            upAdpater.submitList(ResultList)
                        }


                    }

                }

            }

            override fun onFailure(call: Call<Bus>, t: Throwable) {
                Log.d(TAG, "이거오류맞지오잉히잉하잉: $t")
            }

        })


    }

    private fun BUSFravoriteInsert(busfavoriteEntity: TestFavoriteModel) {


        var businsertTask = (object : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {

                activitybusfavoriteEntity = busFavoriteDB.busFavoriteDAO().busFavoriteGetAll()

                val stationnameList = mutableListOf<String>()
                for (i in activitybusfavoriteEntity.indices) {
                    val stationname = activitybusfavoriteEntity.get(i).stationName
                    stationnameList.add(stationname)
                }
                if (binding.BusStationName.text !in stationnameList) {
                    busFavoriteDB.busFavoriteDAO().busFavoriteInsert(busfavoriteEntity)
                }


            }


            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                val stationnameList = mutableListOf<String>()

                for (i in activitybusfavoriteEntity.indices) {
                    val stationname = activitybusfavoriteEntity.get(i).stationName
                    stationnameList.add(stationname)
                }

                if (binding.BusStationName.text in stationnameList) {
                    Toast.makeText(this@MapsActivity, "이미 즐겨찾기에 추가된 정류장입니다!", Toast.LENGTH_SHORT)
                        .show()

                } else {
                    Toast.makeText(this@MapsActivity, "즐겨찾기에 추가 되었습니다!", Toast.LENGTH_SHORT).show()
                    binding.countingstars.setImageResource(R.drawable.shinigstar)
                }

            }
        }).execute()
    }

    private fun busFavoriteGetAll() {
        val busGetAllTask = (object : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                activitybusfavoriteEntity = busFavoriteDB.busFavoriteDAO().busFavoriteGetAll()
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                val stationnameList = mutableListOf<String>()

                for (i in activitybusfavoriteEntity.indices) {
                    val stationname = activitybusfavoriteEntity.get(i).stationName
                    stationnameList.add(stationname)
                }

                if (binding.BusStationName.text in stationnameList) {
                    binding.countingstars.setImageResource(R.drawable.shinigstar)
                } else {
                    binding.countingstars.setImageResource(R.drawable.star)
                }

            }

        }).execute()
    }

    private fun savemystation() = with(binding) {

        countingstars.setOnClickListener {

            val stationName = intent.getStringExtra("stationName").toString()
            val stationNodeNumber = intent.getStringExtra("stationNodeNumber").toString()
            val stationNodeNode = intent.getStringExtra("stationnodenode").toString()

            val hello = TestFavoriteModel(
                id = null,
                checkBoolean = null,
                stationnodenode = stationNodeNode,
                stationName = stationName,
                stationNodeNumber = stationNodeNumber
            )
            BUSFravoriteInsert(hello)
        }
    }


}