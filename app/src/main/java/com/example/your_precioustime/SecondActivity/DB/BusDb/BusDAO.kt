package com.example.your_precioustime.SecondActivity.DB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.your_precioustime.SecondActivity.DB.SubwayDB.TestFavoriteModel


@Dao
interface BusDAO {

    @Insert(onConflict = REPLACE)
    fun businsert(busEntity: BUSEntity)

    @Query("SELECT * FROM busEntity")
    fun busgetAll() : List<BUSEntity>

    @Delete
    fun busDelete(busEntity: BUSEntity)

}

@Dao
interface BusNumberDAO{
    @Insert(onConflict = REPLACE)
    fun busnumInsert(BUSNumEntity: BUSNumEntity)

    @Query("SELECT * FROM busNum")
    fun busnumGetAll() : List<BUSNumEntity>

    @Delete
    fun busnumDelete(BUSNumEntity: BUSNumEntity)
}

@Dao
interface BusStationNameDAO{
    @Insert(onConflict = REPLACE)
    fun busStationNameInsert(bUSStationNameEntity:BUSStationNameEntity)

    @Query("SELECT * FROM busStationName")
    fun busStationNameGetAll() : List<BUSStationNameEntity>

    @Delete
    fun busStationNameDelete(bUSStationNameEntity: BUSStationNameEntity)
}


@Dao
interface BusFavoriteDAO{
    @Insert(onConflict = REPLACE)
    fun busFavoriteInsert(testFavoriteModel: TestFavoriteModel)

    @Query("SELECT * FROM FavoriteModel")
    fun busFavoriteGetAll() : List<TestFavoriteModel>

    @Delete
    fun busFavoriteDelete(testFavoriteModel: TestFavoriteModel)
}