package com.example.your_precioustime.SecondActivity.DB

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "subwaynameEntity")
data class SubwayNameEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Long?,
    var subwayName:String
)

@Entity(tableName = "testModelhi")
data class MyTestModel(
    @PrimaryKey(autoGenerate = true)
    val id : Long?,

    var subwayName: String?,
    var subwayNumberList:List<MyTestSubwayList>
)

data class MyTestSubwayList(
    var subwaynumber:String?
)