package com.example.your_precioustime.Retrofit

import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Retrofit_Client {

    fun getClient(baseurl:String):Retrofit{

        val retrofitClient = Retrofit.Builder()
            .baseUrl(baseurl)
//            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(TikXmlConverterFactory.create(TikXml.Builder().exceptionOnUnreadXml(false).build()))
            .build()

        return retrofitClient

    }
}