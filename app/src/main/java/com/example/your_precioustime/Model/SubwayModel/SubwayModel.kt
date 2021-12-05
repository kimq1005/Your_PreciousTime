package com.example.your_precioustime.Model.SubwayModel


import com.google.gson.annotations.SerializedName

data class SubwayModel(
//    @SerializedName("errorMessage")
//    val errorMessage: ErrorMessage?,
    @SerializedName("realtimeArrivalList")
    val realtimeArrivalList: List<RealtimeArrival>?
)