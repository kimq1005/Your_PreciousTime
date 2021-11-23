package com.example.your_precioustime.SecondActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.your_precioustime.Model.Bus
import com.example.your_precioustime.Model.City
import com.example.your_precioustime.Model.Item
import com.example.your_precioustime.Model.StationBus
import com.example.your_precioustime.R
import com.example.your_precioustime.Retrofit.Retrofit_Client
import com.example.your_precioustime.Retrofit.Retrofit_InterFace
import com.example.your_precioustime.Retrofit.Retrofit_Manager
import com.example.your_precioustime.ThridActivity.BusSubwayActivity
import com.example.your_precioustime.Url
import com.example.your_precioustime.Util.Companion.TAG
import com.example.your_precioustime.databinding.BusFragmentBinding
import kotlinx.android.synthetic.main.subway_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create


class BusFragment:Fragment(R.layout.bus_fragment) {

    lateinit var upAdpater : UpAdpater
    private var busbinding : BusFragmentBinding? =null
    private val binding get() = busbinding!!
    lateinit var hicityCode: String

    private var retrofitInterface: Retrofit_InterFace =
        Retrofit_Client.getClient(Url.BUS_MAIN_URL).create(Retrofit_InterFace::class.java)

    private var retrofitFuckInterFace:Retrofit_InterFace=
        Retrofit_Client.getFuckClient("http://openapi.tago.go.kr/openapi/service/").create(Retrofit_InterFace::class.java)

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        busbinding = BusFragmentBinding.bind(view)

//        Retrofit_Manager.retrofitManager.GETBUS()
        ClickSearchBtn()
        binding.FavoritesBtn.setOnClickListener {
//            val intent = Intent(context,BusSubwayActivity::class.java)
//            startActivity(intent)
//           Retrofit_Manager.retrofitManager.GETCITY()

            stationname(31010,"우남")

        }


    }

    private fun ClickSearchBtn() =with(binding) {


        clickhere.setOnClickListener {
            SetRecyclerView()

        }

    }


    private fun citycodecall()= with(binding){
        val call = retrofitInterface.CityGet()

        call.enqueue(object :Callback<City>{
            override fun onResponse(call: Call<City>, response: Response<City>) {
                Log.d(TAG, "onResponse:${response.body()}")
            }

            override fun onFailure(call: Call<City>, t: Throwable) {
                Log.d(TAG, "onFailure:$t")
            }

        })
    }

    private fun stationname(citycode:Int , stationName:String){
        val call = retrofitFuckInterFace.StationNameGet(citycode,stationName)

        call.enqueue(object :retrofit2.Callback<StationBus>{
            override fun onResponse(call: Call<StationBus>, response: Response<StationBus>) {
                Log.d(TAG, "onResponse: ${response.body()}")
            }

            override fun onFailure(call: Call<StationBus>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
            }

        })
    }

    private fun SetRecyclerView(citycode:String,nodeId:String)=with(binding) {

        upAdpater = UpAdpater()
        val stationName  = SearchEditText.text.toString()
        val suwoncitycode:Int = 31010
        val stationcalls= retrofitInterface.StationNameGet(suwoncitycode,stationName)

        stationcalls.enqueue(object :retrofit2.Callback<StationBus>{
            override fun onResponse(call: Call<StationBus>, response: Response<StationBus>) {
                val body = response.body()

            }

            override fun onFailure(call: Call<StationBus>, t: Throwable) {
                Log.d(TAG, "onFailure:$t")
            }

        })



        val buscall = retrofitInterface.BusGet(citycode,nodeId)

        buscall.enqueue(object:retrofit2.Callback<Bus>{
            override fun onResponse(call: Call<Bus>, response: Response<Bus>) {
                val body = response.body()
                val higg = body?.body?.items
                Log.d(TAG, "onResponse: ${higg}")
                body?.let{Bus->

                    busRecyclerView.apply {
                        val hello = Bus.body.items.item
                        adapter=upAdpater
                        upAdpater.submitList(hello)
                        layoutManager = LinearLayoutManager(context)
                    }

                }
            }

            override fun onFailure(call: Call<Bus>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
            }

        })


    }
}


