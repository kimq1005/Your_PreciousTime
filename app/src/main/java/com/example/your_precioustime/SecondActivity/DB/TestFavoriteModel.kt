package com.example.your_precioustime.SecondActivity.DB

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "FavoriteModel")
data class TestFavoriteModel (
    @PrimaryKey(autoGenerate = true)
    var id:Long?,

    var checkBoolean: Boolean?,

    var stationName:String,
    var stationNodeNumber:String
)

data class wowyeah(
    var stationName: String,
    var stationNodeNumber: String
)