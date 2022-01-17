package com.example.your_precioustime.ObjectManager

import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.your_precioustime.Model.StationBus
import com.example.your_precioustime.Retrofit.Retrofit_Client
import com.example.your_precioustime.Retrofit.Retrofit_InterFace
import com.example.your_precioustime.SecondActivity.Busfragment.Bus_Station_Search_Adapter
import com.example.your_precioustime.Url
import com.example.your_precioustime.Util
import retrofit2.Call
import retrofit2.Response

class RetrofitObject {

    private var retrofitInterface: Retrofit_InterFace =
        Retrofit_Client.getClient(Url.BUS_MAIN_URL).create(Retrofit_InterFace::class.java)

    companion object{
        val retrofitObject = RetrofitObject()
    }


    private fun ClickSearchBtn(clickhere: View, contextEditText: EditText) {

        clickhere.setOnClickListener {
            val suwoncitycode: String = "31010"
            val StationEditName = contextEditText.text.toString()
            SetRecyclerView(suwoncitycode, StationEditName)
//            hellomy(suwoncitycode,"GGB203000129")
            //버스 띄우고 지하철은 그림판가져와서 한번 해보자 내일이면 충분함 띄우는건 ㅇㅋ? 굳
        }

    }


    fun SetRecyclerView(citycode: String, stationName: String?) {

        val stationcalls = retrofitInterface.StationNameGet(
            cityCode = citycode,
            staionName = stationName
        )

        stationcalls.enqueue(object : retrofit2.Callback<StationBus> {
            override fun onResponse(call: Call<StationBus>, response: Response<StationBus>) {
                val body = response.body()

            }


            override fun onFailure(call: Call<StationBus>, t: Throwable) {
                android.util.Log.d(Util.TAG, "onFailure:$t")

            }

        })


    }

}