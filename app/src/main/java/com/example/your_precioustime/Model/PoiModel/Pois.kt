package com.example.your_precioustime.Model.PoiModel


import com.google.gson.annotations.SerializedName

data class Pois(
    @SerializedName("poi")
    val poi:List<Poi>
)