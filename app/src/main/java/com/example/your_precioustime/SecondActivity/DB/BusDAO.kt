package com.example.your_precioustime.SecondActivity.DB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query


@Dao
interface BusDAO {

    @Insert(onConflict = REPLACE)
    fun businsert(busEntity: BUSEntity)

    @Query("SELECT * FROM busEntity")
    fun busgetAll() : List<BUSEntity>

    @Delete
    fun busDelete(busEntity: BUSEntity)

}