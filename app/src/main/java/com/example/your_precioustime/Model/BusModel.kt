package com.example.your_precioustime.Model

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

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


@Xml(name="response")
data class PillInfo(
    @Element
    val header: Header,

    @Element
    val body:Body
)


@Xml(name="header")
data class Header(
    @PropertyElement
    val resultCode:Int,

    @PropertyElement
    val resultMsg:String
)


@Xml(name = "body")
data class Body(
    @PropertyElement
    val item :item
)

@Xml(name="item")
data class item(
    @PropertyElement
    val arrtime: String,

    @PropertyElement
    val nodenm: String,

)
