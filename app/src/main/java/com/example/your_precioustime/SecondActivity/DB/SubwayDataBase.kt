package com.example.your_precioustime.SecondActivity.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(SubwayNameEntity::class), version = 2)
abstract class SubwayDataBase : RoomDatabase() {
    abstract fun subwayNameDAO(): SubwayNameDAO

    companion object{
        private var INSTANCE: SubwayDataBase? = null

        fun getinstance(context: Context): SubwayDataBase? {
            if(INSTANCE ==null){
                synchronized(SubwayDataBase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    SubwayDataBase::class.java,"subwayname.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            return INSTANCE
        }
    }
}