package com.example.your_precioustime.Retrofit

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.your_precioustime.Model.Bus
import com.example.your_precioustime.Model.BusModel
import com.example.your_precioustime.Model.ParsingModel
import com.example.your_precioustime.SecondActivity.BusItem
import com.example.your_precioustime.SecondActivity.UpAdpater
import com.example.your_precioustime.Url
import com.example.your_precioustime.Util.Companion.TAG
import com.google.gson.JsonElement
import com.tickaroo.tikxml.TikXml
import org.xmlpull.v1.builder.XmlElement
import retrofit2.Call
import retrofit2.Response
import retrofit2.create
import java.lang.Exception

class Retrofit_Manager {
    lateinit var upAdpater: UpAdpater

    companion object{
        val retrofitManager = Retrofit_Manager()
    }


    private var retrofitInterface:Retrofit_InterFace =
        Retrofit_Client.getClient(Url.BUS_MAIN_URL).create(Retrofit_InterFace::class.java)


    fun GETBUS(){


        val call = retrofitInterface.BusGet("25","DJB8001793")

        call.enqueue(object:retrofit2.Callback<Bus>{
            override fun onResponse(call: Call<Bus>, response: Response<Bus>) {
                Log.d(TAG, "onResponse:${response.raw()}")

                val body = response.body()
                try{
                    body.let{


                    }
                }catch (e:Exception){
                    print(e)
                }

//                when(response.code()){
//
//                    200-> response.body().let{
//
//                        val model = ArrayList<ParsingModel>()
//                        val hi = listOf(it?.body?.items)
//                        Log.d(TAG, "sdfsf:$hi")
//
//
//                    }
//                }
            }

            override fun onFailure(call: Call<Bus>, t: Throwable) {
                Log.d(TAG, "onFailure: $t ")
            }

        })
    }
}