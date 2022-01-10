package com.example.your_precioustime.SecondActivity.DB.SubwayDB

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "subwaynameEntity")
data class SubwayNameEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Long?,
    var subwayName:String
)