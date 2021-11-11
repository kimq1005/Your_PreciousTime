package com.example.your_precioustime.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Retrofit_Client {

    fun getClient(baseurl:String):Retrofit{

        val retrofitClient = Retrofit.Builder()
            .baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofitClient

    }
}