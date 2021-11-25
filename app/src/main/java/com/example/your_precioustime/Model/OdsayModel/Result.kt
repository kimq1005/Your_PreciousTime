package com.example.your_precioustime.Model.OdsayModel


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("station")
    val station: List<Station>?
)