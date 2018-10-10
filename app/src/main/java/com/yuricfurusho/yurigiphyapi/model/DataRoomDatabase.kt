package com.yuricfurusho.yurigiphyapi.model

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.IO
import kotlinx.coroutines.experimental.launch

/**
 * Created by Yuri Furusho on 25/09/18.
 */
@Database(entities = arrayOf(Data::class), version = 1)
public abstract class DataRoomDatabase : RoomDatabase() {

    abstract fun dataDao(): DataDao

    companion object {
        @Volatile
        private var INSTANCE: DataRoomDatabase? = null

        fun getDatabase(context: Context,
                        scope: CoroutineScope): DataRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                // Create database here
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        DataRoomDatabase::class.java,
                        "Data_database"
                )
                        .fallbackToDestructiveMigration()
                        .addCallback(DataDatabaseCallback(scope))
                        .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class DataDatabaseCallback(
            private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.dataDao())
                }
            }
        }

        fun populateDatabase(dataDao: DataDao) {
//            dataDao.deleteAll()

//            var data = Data("Hello")
//            dataDao.insert(data)
//            data = Data("World!")
//            dataDao.insert(data)
        }
    }
}