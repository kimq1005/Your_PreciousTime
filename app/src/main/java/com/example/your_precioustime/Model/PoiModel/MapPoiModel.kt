package com.example.your_precioustime.Model.PoiModel


import com.google.gson.annotations.SerializedName

data class MapPoiModel(
    @SerializedName("searchPoiInfo")
    val searchPoiInfo: SearchPoiInfo?
)