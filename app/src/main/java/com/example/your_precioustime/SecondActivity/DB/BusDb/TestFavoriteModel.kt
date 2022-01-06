package com.example.your_precioustime.SecondActivity.DB.SubwayDB

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "FavoriteModel")
data class TestFavoriteModel (
    @PrimaryKey(autoGenerate = true)
    var id:Long?,

    var checkBoolean: Boolean?,
    var stationnodenode:String,
    var stationName:String,
    var stationNodeNumber:String
)


@Entity(tableName = "duplicationList")
data class DuplicationList(
    @PrimaryKey(autoGenerate = true)
    var id:Long?,

    var checkBoolean: Boolean?,
    var stationnodenode:String,
    var stationName:String,
    var stationNodeNumber:String
)