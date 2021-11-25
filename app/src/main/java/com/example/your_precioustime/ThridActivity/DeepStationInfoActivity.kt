package com.example.your_precioustime.ThridActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.your_precioustime.Model.Bus
import com.example.your_precioustime.Retrofit.Retrofit_Client
import com.example.your_precioustime.Retrofit.Retrofit_InterFace
import com.example.your_precioustime.SecondActivity.UpAdpater
import com.example.your_precioustime.Url
import com.example.your_precioustime.Util.Companion.TAG
import com.example.your_precioustime.databinding.ActivityDeepStationInfoBinding
import retrofit2.Call
import retrofit2.Response

class DeepStationInfoActivity : AppCompatActivity() {

    private var deepStationbinding:ActivityDeepStationInfoBinding? =null
    private val binding get() = deepStationbinding!!

    private lateinit var upAdpater:UpAdpater



    private val retrofitInterface: Retrofit_InterFace = Retrofit_Client.getClient(Url.BUS_MAIN_URL).create(Retrofit_InterFace::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        deepStationbinding = ActivityDeepStationInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val stationName = intent.getStringExtra("stationName").toString()
        val stationNodeNumber = intent.getStringExtra("stationNodeNumber").toString()


//        Log.d(TAG, "onCreate: $stationName , $stationNodeNumber")

        binding.BusStationName.text = stationName
        SetBusStationRecyclerView()



    }

    private fun SetBusStationRecyclerView()=with(binding) {
        val stationName = intent.getStringExtra("stationName").toString()
        val stationNodeNumber = intent.getStringExtra("stationNodeNumber").toString()
        val citycode:String = "31010"
        val call = retrofitInterface.BusGet(citycode,stationNodeNumber)
        call.enqueue(object:retrofit2.Callback<Bus>{
            override fun onResponse(call: Call<Bus>, response: Response<Bus>) {
                Log.d(TAG, "onResponse: ${response.body()}")
                upAdpater = UpAdpater()

                val body = response.body()

                body?.let{

                    val wow = body.body.items.item
                    deepstationinfoRecyclerView.apply {
                        adapter = upAdpater
                        layoutManager = LinearLayoutManager(context)
                        upAdpater.submitList(wow)
                    }


                }

            }

            override fun onFailure(call: Call<Bus>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
            }

        })




    }


}