package com.example.your_precioustime.SecondActivity.DB

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


@Dao
interface SubwayNameDAO {
    @Insert(onConflict = REPLACE)
    fun subwayInsert(subwayNameEntity: SubwayNameEntity)

    @Query("SELECT * FROM subwaynameEntity")
    fun subwayGetAll() : List<SubwayNameEntity>

    @Delete
    fun subwayDelete(subwayNameEntity: SubwayNameEntity)
}