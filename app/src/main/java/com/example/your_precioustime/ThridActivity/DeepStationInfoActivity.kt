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



                    val test = hello[1]
                    val test2 = hello[2]
                    val test3 = hello[3]
                    val test4 = hello[4]
                    val test5 = hello[5]


                    val hi = mutableListOf<String>()

                    for(i in hello.indices){
                        val busNm:String
                        val waitbus:Int

                        busNm = hello.get(i).toString()
                        hi.add(busNm)

                    }
                    Log.d(TAG, "\n 홀리퍼큉쉣맨: $hi\n\n\n")

//                    val afafafa= hi.filter { it.length % 2 == 1 }
//                    Log.d(TAG, "onResponse: ${afafafa.indices}")

//                    for(i in 0..hi.size){
//                        hi.removeAt(i+1)
//                        Log.d(TAG, "onResponse:$hi")
//                    }

                    Log.d(TAG, "onResponse:${hi.size.toInt()}")


                    Log.d(TAG, "ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ\n")
//                    hi.removeAt(1)
//                    hi.removeAt(2)
//                    hi.removeAt(3)
//                    Log.d(TAG, "onResponse: $hi")

//                    val hfifaf = hi.filter { it.length % 2 == 0 }
//                    Log.d(TAG, "afafafafafadaf: $hfifaf")



//                    Log.d(TAG, "버스노선정보: ${hi.distinct()}")6



//                    deepstationinfoRecyclerView.apply {
//                        adapter = upAdpater
//                        layoutManager = LinearLayoutManager(context)
//                        upAdpater.submitList(wow)
//                    }


                }

            }

            override fun onFailure(call: Call<Bus>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
            }

        })




    }


}