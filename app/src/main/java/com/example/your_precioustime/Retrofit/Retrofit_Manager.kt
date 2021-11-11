package com.example.your_precioustime.Retrofit

import android.util.Log
import com.example.your_precioustime.Model.BusModel
import com.example.your_precioustime.Url
import com.example.your_precioustime.Util.Companion.TAG
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Response
import retrofit2.create

class Retrofit_Manager {

    companion object{
        val retrofitManager = Retrofit_Manager()
    }


    private var retrofitInterface:Retrofit_InterFace =
        Retrofit_Client.getClient(Url.BUS_MAIN_URL).create(Retrofit_InterFace::class.java)


    fun GETBUS(){
        val call = retrofitInterface.BusGet(Url.BUS_API_KEY,"25","dDJB8001793d")

        call.enqueue(object:retrofit2.Callback<BusModel>{
            override fun onResponse(call: Call<BusModel>, response: Response<BusModel>) {
                Log.d(TAG, "onResponse:${response.body()}")
            }

            override fun onFailure(call: Call<BusModel>, t: Throwable) {
                Log.d(TAG, "onFailure: $t ")
            }

        })
    }
}