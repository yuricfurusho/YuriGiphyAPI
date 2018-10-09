package com.yuricfurusho.yurigiphyapi.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * Created by Yuri Furusho on 25/09/18.
 */
@Database(entities = arrayOf(Data::class), version = 1)
public abstract class DataRoomDatabase : RoomDatabase() {

    abstract fun dataDao(): DataDao

    companion object {
        @Volatile
        private var INSTANCE: DataRoomDatabase? = null

        fun getDatabase(context: Context): DataRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                // Create database here
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        DataRoomDatabase::class.java,
                        "Data_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}