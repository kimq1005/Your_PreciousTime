package com.example.your_precioustime.Model.OdsayModel


import com.google.gson.annotations.SerializedName

data class Station(
    @SerializedName("arsID")
    val arsID: String?,
    @SerializedName("ebid")
    val ebid: String?,
    @SerializedName("laneCity")
    val laneCity: String?,
    @SerializedName("laneName")
    val laneName: String?,
    @SerializedName("nonstopStation")
    val nonstopStation: Int?,
    @SerializedName("stationClass")
    val stationClass: Int?,
    @SerializedName("stationID")
    val stationID: Int?,
    @SerializedName("stationName")
    val stationName: String?,
    @SerializedName("type")
    val type: Int?,
    @SerializedName("x")
    val x: Double?,
    @SerializedName("y")
    val y: Double?
)