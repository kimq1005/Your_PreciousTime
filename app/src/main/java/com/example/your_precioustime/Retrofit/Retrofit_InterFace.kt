package com.example.your_precioustime.Retrofit

import com.example.your_precioustime.Model.*


import com.example.your_precioustime.Url
import com.google.gson.JsonElement
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.annotation.Xml
import org.xmlpull.v1.builder.XmlElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface Retrofit_InterFace {

    @GET(Url.BUS_GET_URL)
    fun BusGet(
//        @Header("serviceKey") serviceKey:String,
        @Query("cityCode") cityCode:String,
        @Query("nodeId") nodeId:String

    ): Call<Bus>


    @GET(Url.BUS_NAME_SEARCH)
    fun StationNameGet(
        @Query("cityCode") cityCode:String,
        @Query("nodeNm") staionName:String?,
//        @Query("nodeNo") nodeNo: Int?
    ) :Call<StationBus>



    @GET(Url.BUS_CITY_URL)
    fun CityGet(
//        @Query("serviceKey") serviceKey:String
    ) :Call<City>


}

