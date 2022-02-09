package com.example.your_precioustime.SecondActivity.DB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subwayEntity")
data class SubwayEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Long?,

    var subwayId:String?,
    var trainLineNm : String?,
    var bstatnNm:String?,
    var arvlMsg2 : String?,

)