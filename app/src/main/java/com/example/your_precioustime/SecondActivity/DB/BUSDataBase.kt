package com.example.your_precioustime.SecondActivity.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(entities = arrayOf(BUSEntity::class),version = 1)
abstract class BUSDataBase : RoomDatabase(){
    abstract fun busDAO() : BusDAO

    companion object{
        private var INSTANCE : BUSDataBase? =null

        fun getinstance(context: Context) : BUSDataBase?{
            if(INSTANCE==null){
                synchronized(BUSDataBase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    BUSDataBase::class.java, "bus.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            return INSTANCE
        }
    }
}