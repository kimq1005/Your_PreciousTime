package com.example.your_precioustime.TestMapActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.your_precioustime.Model.StationBus
import com.example.your_precioustime.R
import com.example.your_precioustime.Retrofit.Retrofit_Client
import com.example.your_precioustime.Retrofit.Retrofit_InterFace
import com.example.your_precioustime.SecondActivity.Busfragment.Bus_Station_Search_Adapter
import com.example.your_precioustime.Url
import com.example.your_precioustime.Util
import com.example.your_precioustime.Util.Companion.TAG

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.example.your_precioustime.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.*
import retrofit2.Call
import retrofit2.Response
import kotlin.math.log

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    lateinit var testfuck : MutableList<StationBus>

    private var mymarker : Marker? = null


    private var retrofitInterface: Retrofit_InterFace =
        Retrofit_Client.getClient(Url.BUS_MAIN_URL).create(Retrofit_InterFace::class.java)

    lateinit var busStationSearchAdapter: Bus_Station_Search_Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ClickSearchBtn()



    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        setMap()

        //마커가즈아

        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        //일단 제가 원하는건 api로 설정해서 내가 검색한 정류장에대한 위치가 지도에 표시가 되어야해요
    }

    private fun setMap(){

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun ClickSearchBtn() = with(binding) {

        clickhere.setOnClickListener {
            val suwoncitycode: String = "31010"
            val StationEditName = SearchEditText2.text.toString()
            SetRecyclerView(suwoncitycode, StationEditName)
        }
    }

//    private fun setupMarker(searchResult: SearchResultEntity): Marker? {
//        Log.d(TAG, "setupMarker: ${searchResult.locationLatLng.latitude.toDouble()}")
//        val positionLatLng = LatLng(
//            searchResult.locationLatLng.latitude.toDouble(),
//            searchResult.locationLatLng.longitued.toDouble()
//        )
//
//        val markerOptions = MarkerOptions().apply {
//            position(positionLatLng)
//            title(searchResult.name)
//            snippet(searchResult.fullAdress)
//        }
//
//        map.moveCamera(CameraUpdateFactory.newLatLngZoom(positionLatLng, CAMERA_ZOOM_LEVEL))
//        return map.addMarker(markerOptions)
//    }

    fun SetRecyclerView(citycode: String, stationName: String?) = with(binding) {

        val stationcalls = retrofitInterface.StationNameGet(
            cityCode = citycode,
            staionName = stationName
        )

        stationcalls.enqueue(object : retrofit2.Callback<StationBus> {

            override fun onResponse(call: Call<StationBus>, response: Response<StationBus>) {
                val body = response.body()

                val myLocationlatlng = LatLngBounds.Builder()
//                Log.d(TAG, "onResponse: $body")
                busStationSearchAdapter = Bus_Station_Search_Adapter()

                body?.let { it ->
                    val hello = body.body.items.item
                    busreclerView.apply {
                        adapter = busStationSearchAdapter
                        layoutManager = LinearLayoutManager(context)
                        busStationSearchAdapter.submitList(hello)
                    }

                    for (i in hello.indices) {
                        val xLocation = hello.get(i).gpslati?.toDouble()!!
                        val yLocation = hello.get(i).gpslong?.toDouble()!!
                        val mapStationname = hello.get(i).nodenm?.toString()!!
                        val position = LatLng(xLocation, yLocation)

                        val marker = MarkerOptions().position(position).title(mapStationname)

                        myLocationlatlng.include(position)
                        mMap.addMarker(marker)

                    }

                    val bounds = myLocationlatlng.build()
                    val padding = 0
                    val camera = CameraUpdateFactory.newLatLngBounds(bounds, padding)

                    mMap.moveCamera(camera)


                }
            }

            override fun onFailure(call: Call<StationBus>, t: Throwable) {
                Log.d(Util.TAG, "onFailure:$t")

            }

        })


    }


}