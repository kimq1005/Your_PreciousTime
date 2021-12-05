package com.example.your_precioustime.SecondActivity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.your_precioustime.Model.OdsayModel.OdasyModel
import com.example.your_precioustime.Model.PoiModel.MapPoiModel
import com.example.your_precioustime.Model.SubwayModel.ErrorMessage
import com.example.your_precioustime.Model.SubwayModel.SubwayModel
import com.example.your_precioustime.R
import com.example.your_precioustime.Retrofit.Retrofit_Client
import com.example.your_precioustime.Retrofit.Retrofit_InterFace
import com.example.your_precioustime.Url
import com.example.your_precioustime.Util.Companion.TAG
import com.example.your_precioustime.databinding.SubwayFragmentBinding
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Response

class SubwayFragment:Fragment(R.layout.subway_fragment) {
    private var setbinding : SubwayFragmentBinding? =null
    private val binding get() = setbinding!!

    lateinit var subWayAdapter: SubWayAdapter
    private var retrofitInterface: Retrofit_InterFace = Retrofit_Client.getJsonClienet("https://api.odsay.com/").create(
        Retrofit_InterFace::class.java
    )

    private var seoulRetrofit_InterFace:Retrofit_InterFace = Retrofit_Client.getJsonClienet(Url.SEOUL_SUBWAY_MAIN_URL).create(
        Retrofit_InterFace::class.java
    )



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setbinding = SubwayFragmentBinding.bind(view)

        binding.clickhere.setOnClickListener {
           SeoulSubwayGet("수원")
        }

//        setRecyclerView()

    }

    private fun SeoulSubwayGet(statNm:String){
        val call = seoulRetrofit_InterFace.SUBWAYGET(
            statnNm = statNm
        )


        call.enqueue(object :retrofit2.Callback<SubwayModel>{
            override fun onResponse(call: Call<SubwayModel>, response: Response<SubwayModel>) {
                val body = response.body()

                body?.let{

                }
                Log.d(TAG, "지하철 정보를보거라 쉽색기야: ${response.body()}")
            }

            override fun onFailure(call: Call<SubwayModel>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
            }

        })
    }

    private fun setRecyclerView()=with(binding) {

        val hello:String = ""
        subWayAdapter = SubWayAdapter()
        val hi = listOf(
            SubwayItem("매탄권선","역5")
        )

        clickhere.setOnClickListener {
            SubwayStopNameTextView.text = RealSearchEditText.text.toString()
            RealSearchEditText.setText("")

            subwayRecyclerView.apply {
                adapter = subWayAdapter
                subWayAdapter.submitList(hi)
                layoutManager = LinearLayoutManager(context)


            }

        }



    }

    private fun testAPI(keyword:String){
        val call = retrofitInterface.MapLocationGet(
            searchKeyword = keyword
        )

        call.enqueue(object :retrofit2.Callback<MapPoiModel>{
            override fun onResponse(call: Call<MapPoiModel>, response: Response<MapPoiModel>) {
                Log.d(TAG, "맵의 위치를 가져오거라 ${response.body()}")
                val body = response.body()
                //noorLon : X좌표
                //noorLat: Y좌표
                body?.let{
                    val Xm = body.searchPoiInfo?.pois?.poi?.get(1)?.noorLon
                    val Ym = body.searchPoiInfo?.pois?.poi?.get(1)?.noorLat

                    Log.d(TAG, "onResponse: $Xm, $Ym")
                }
            }

            override fun onFailure(call: Call<MapPoiModel>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
            }

        })
    }

    private fun ODSAYAPI(){
        val call = retrofitInterface.ODSAYMapLocationGet(

            xm = 127.04055197,
            ym = 37.25324165
        )

        call.enqueue(object :retrofit2.Callback<JsonElement>{
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "onResponse: ${response.raw()}")
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "onFailure:$t")
            }

        })
    }
}