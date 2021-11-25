package com.example.your_precioustime.Model.PoiModel


import com.google.gson.annotations.SerializedName

data class Poi(
    @SerializedName("noorLon")
    val noorLon: String?,

    @SerializedName("noorLat")
    val noorLat: String?

)