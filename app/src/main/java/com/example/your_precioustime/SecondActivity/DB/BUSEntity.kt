package com.example.your_precioustime.SecondActivity.DB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "busEntity")
data class BUSEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Long? = null,

    var busNumber:String? = null,
    var arriveStation:String? = null,
    var waitTime:String? = null
)


@Entity(tableName = "busNum")
data class BUSNumEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Long?,

    var busNumber: String?
)