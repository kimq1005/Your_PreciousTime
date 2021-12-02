package com.example.your_precioustime.Retrofit

import com.example.your_precioustime.Model.StationBus
import com.example.your_precioustime.Url
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Coroutines_InterFace {

    @GET(Url.BUS_NAME_SEARCH)
    fun Coroutines_BUS_NAMEGET(
        @Query("cityCode") cityCode:String,
        @Query("nodeNm") staionName:String?,
//        @Query("nodeNo") nodeNo: Int?
    ) : Response<StationBus>
}