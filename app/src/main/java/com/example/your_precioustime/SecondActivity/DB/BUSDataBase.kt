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


@Database(entities = arrayOf(BUSNumEntity::class),version = 1)
abstract class BUSDNumberDataBase : RoomDatabase(){
    abstract fun busnumDAO() : BusNumberDAO

    companion object{
        private var INSTANCE : BUSDNumberDataBase? =null

        fun getinstance(context: Context) : BUSDNumberDataBase?{
            if(INSTANCE==null){
                synchronized(BUSDNumberDataBase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        BUSDNumberDataBase::class.java, "busnum.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            return INSTANCE
        }
    }
}

@Database(entities = arrayOf(BUSStationNameEntity::class),version = 1)
abstract class BUSStationNameDataBase : RoomDatabase(){
    abstract fun busstationnameDao() : BusStationNameDAO

    companion object{
        private var INSTANCE : BUSStationNameDataBase? =null

        fun getinstance(context: Context) : BUSStationNameDataBase?{
            if(INSTANCE==null){
                synchronized(BUSStationNameDataBase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        BUSStationNameDataBase::class.java, "busstationname.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            return INSTANCE
        }
    }
}

@Database(entities = arrayOf(TestFavoriteModel::class),version = 3)
abstract class BusFavroiteDataBase : RoomDatabase(){
    abstract fun busFavoriteDAO() : BusFavoriteDAO

    companion object{
        private var INSTANCE : BusFavroiteDataBase? =null

        fun getinstance(context: Context) : BusFavroiteDataBase?{
            if(INSTANCE==null){
                synchronized(BusFavroiteDataBase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        BusFavroiteDataBase::class.java, "busfravorite.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            return INSTANCE
        }
    }
}