package com.example.your_precioustime.SecondActivity.DB.SubwayDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query


@Dao
interface SubwayDAO {

    @Insert(onConflict = REPLACE)
    fun subwayInsert(subwayEntity: SubwayEntity)

    @Query("SELECT * FROM subwayEntity")
    fun subwayGetAll() : List<SubwayEntity>

    @Delete
    fun subwayDelete(subwayEntity: SubwayEntity)
}