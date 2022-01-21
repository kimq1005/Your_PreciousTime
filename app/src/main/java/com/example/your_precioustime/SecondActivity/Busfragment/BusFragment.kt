package com.example.your_precioustime.SecondActivity.Busfragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.your_precioustime.Model.*
import com.example.your_precioustime.R
import com.example.your_precioustime.Retrofit.Retrofit_Client
import com.example.your_precioustime.Retrofit.Retrofit_InterFace
import com.example.your_precioustime.Url
import com.example.your_precioustime.Util.Companion.TAG
import com.example.your_precioustime.databinding.BusFragmentBinding
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Response
import kotlin.Exception
import kotlin.coroutines.CoroutineContext


class BusFragment:Fragment(R.layout.bus_fragment),CoroutineScope {

    private var busbinding :BusFragmentBinding? =null
    private val binding get() = busbinding!!
    lateinit var busStationSearchAdapter: Bus_Station_Search_Adapter



    lateinit var job:Job

    override val coroutineContext : CoroutineContext
        get() = Dispatchers.Main + job


    private var retrofitInterface: Retrofit_InterFace =
        Retrofit_Client.getClient(Url.BUS_MAIN_URL).create(Retrofit_InterFace::class.java)

    private var retrofitFuckInterFace:Retrofit_InterFace=
        Retrofit_Client.getFuckClient(Url.ODSAY_BASE_URL).create(Retrofit_InterFace::class.java)
    //코루틴 연습 가자잇


    override fun onAttach(context: Context) {
        super.onAttach(context)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        busbinding = BusFragmentBinding.bind(view)
        job = Job()

        val suwoncitycode:String = "31010"
        val StationEditName = binding.SearchEditText.text.toString()
        SetRecyclerView(suwoncitycode,null)
//            hellomy(suwoncitycode,"GGB203000129")

        ClickSearchBtn()



    }


    private fun ClickSearchBtn() =with(binding) {

        clickhere.setOnClickListener {
            val suwoncitycode:String = "31010"
            val StationEditName = SearchEditText.text.toString()
            SetRecyclerView(suwoncitycode,StationEditName)
//            hellomy(suwoncitycode,"GGB203000129")
            //경
       }

        SearchEditText.setOnClickListener {
            noResultTextView.visibility = View.INVISIBLE
        }

    }




    fun SetRecyclerView(citycode:String,stationName:String?)=with(binding) {

        val stationcalls= retrofitInterface.StationNameGet(
            cityCode = citycode,
            staionName = stationName,
            null
        )

        stationcalls.enqueue(object :retrofit2.Callback<StationBus>{

            override fun onResponse(call: Call<StationBus>, response: Response<StationBus>) {
                val body = response.body()
                busStationSearchAdapter = Bus_Station_Search_Adapter()

                body?.let{it->
                    val hello = body.body.items.item
                    busRecyclerView.apply {
                        adapter = busStationSearchAdapter
                        layoutManager = LinearLayoutManager(context)
                        busStationSearchAdapter.submitList(hello)
                    }
                }
           }
            override fun onFailure(call: Call<StationBus>, t: Throwable) {
                Log.d(TAG, "onFailure:$t")
                noResultTextView.visibility = View.VISIBLE

            }

        })






//        val buscall = retrofitInterface.BusGet(citycode,nodeId)
//
//        buscall.enqueue(object:retrofit2.Callback<Bus>{
//            override fun onResponse(call: Call<Bus>, response: Response<Bus>) {
//                val body = response.body()
//                val higg = body?.body?.items
//                Log.d(TAG, "onResponse: ${higg}")
//                body?.let{Bus->
//
//                    busRecyclerView.apply {
//                        val hello = Bus.body.items.item
//                        adapter=upAdpater
//                        upAdpater.submitList(hello)
//                        layoutManager = LinearLayoutManager(context)
//                    }
//
//                }
//            }
//
//            override fun onFailure(call: Call<Bus>, t: Throwable) {
//                Log.d(TAG, "onFailure: $t")
//            }
//
//        })

    }

}


