package com.example.your_precioustime.Model

import java.io.Serializable

data class BusModel(
    val nodeid:String,
    val nodenm:String,
    val routeid:String,
    val routeno:String,
    val routetp:String,
    val arrprevstationcnt:String,
    val vehicletp:String,
    val arrtime:String
):Serializable