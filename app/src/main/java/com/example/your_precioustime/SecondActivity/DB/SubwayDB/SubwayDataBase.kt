package com.example.your_precioustime.SecondActivity.DB.SubwayDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(SubwayEntity::class), version = 1)
abstract class SubwayDataBase : RoomDatabase() {
    abstract fun subwayDAO(): SubwayDAO

    companion object{
        private var INSTANCE: SubwayDataBase? = null

        fun getinstance(context: Context): SubwayDataBase? {
            if(INSTANCE==null){
                synchronized(SubwayDataBase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    SubwayDataBase::class.java,"subway.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            return INSTANCE
        }
    }
}