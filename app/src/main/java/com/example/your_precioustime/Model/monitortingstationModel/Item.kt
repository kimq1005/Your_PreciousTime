package com.example.your_precioustime.Model.monitortingstationModel


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("addr")
    val addr: String?,
    @SerializedName("stationName")
    val stationName: String?,
    @SerializedName("tm")
    val tm: Double?
)