package com.example.your_precioustime.Model.PoiModel


import com.google.gson.annotations.SerializedName

data class SearchPoiInfo(
//    @SerializedName("count")
//    val count: String?,
//    @SerializedName("page")
//    val page: String?,
    @SerializedName("pois")
    val pois: Pois?,
//    @SerializedName("totalCount")
//    val totalCount: String?
)