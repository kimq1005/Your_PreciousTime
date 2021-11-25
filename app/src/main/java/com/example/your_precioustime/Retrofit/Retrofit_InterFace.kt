package com.example.your_precioustime.Retrofit

import com.example.your_precioustime.Model.*
import com.example.your_precioustime.Model.OdsayModel.OdasyModel
import com.example.your_precioustime.Model.PoiModel.MapPoiModel


import com.example.your_precioustime.Url
import retrofit2.Call
import retrofit2.http.GET
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

    @GET(Url.TMAP_LOCATION_URL)
    fun MapLocationGet(
        @Query("version") version:Int=1,
        @Query("appKey") appKey:String= Url.TMAP_API_KEY,
        @Query("searchKeyword") searchKeyword:String
    ): Call<MapPoiModel>


    @GET(Url.ODSAY_POI_URL)
    fun ODSAYMapLocationGet(
//        @Query("apiKey") apiKey:String="P0AqcoST/h1VEbQSLktxxv6OqLdACYARAIrxgmcYC5E",
        @Query("lang") lang:Int = 0,
        @Query("x") xm:Double?,
        @Query("y") ym:Double?,
        @Query("stationClass") stationClass:Int = 2
    ):Call<OdasyModel>


}

