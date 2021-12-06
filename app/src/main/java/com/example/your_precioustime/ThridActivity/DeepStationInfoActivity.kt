package com.example.your_precioustime.ThridActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.your_precioustime.Model.Bus
import com.example.your_precioustime.Model.Item
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
        binding.backbtn.setOnClickListener {
            onBackPressed()
        }

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

                    val hello =body.body.items.item
//
//                    val hi = mutableListOf<String>()
//
//                    for ( i in hello.indices){
//
//                        val wowowowwo = hello[i]
//
//                        hi.add(wowowowwo.toString())
//
//                    }
//
//
//
//                    Log.d(TAG, "꾸에에에ㅔㅔ에엑: $hi ")
//
//                    for ( i in hi){
//                        val fuckcckckckchisaf = hi.filter { it.length % 2 == 1 }
//                        Log.d(TAG, "onResponse: $fuckcckckckchisaf")
//                    }
//                    val studingfuck = hi.length
//                    Log.d(TAG, "야이시발되라고 좀 진짜 아 ㅡ: $studingfuck")
//
//                    val hoyo = hi.filter { it.length % 2 == 1 }
//                    Log.d(TAG, "이에에에에ㅔ에에에ㅔㄱ : ${hoyo} ")



                    val hi = mutableListOf<Item>()

                    for(i in hello.indices){
                        val busNm:String
                        val waitbus:Int

                        busNm = hello.get(i).routeno!!
                        waitbus = hello.get(i).arrprevstationcnt!!
                        hi.add(Item(
                            busNm,waitbus
                        ))

                    }
                    Log.d(TAG, "\n 홀리퍼큉쉣맨: $hi\n\n\n")

                    Log.d(TAG, "ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ\n")

                    val afafafa= hi.filterIndexed { index, i ->
                        index %2 ==0
                    }

                    Log.d(TAG, "onResponse: ${afafafa}")


                    deepstationinfoRecyclerView.apply {
                        adapter = upAdpater
                        layoutManager = LinearLayoutManager(context)
                        upAdpater.submitList(afafafa)
                    }


                }

            }

            override fun onFailure(call: Call<Bus>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
            }

        })




    }


}