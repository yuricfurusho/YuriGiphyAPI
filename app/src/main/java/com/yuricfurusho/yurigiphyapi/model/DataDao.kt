package com.yuricfurusho.yurigiphyapi.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

/**
 * Created by Yuri Furusho on 25/09/18.
 */
@Dao
interface DataDao {

    @Query("SELECT * from Data ORDER BY id ASC")
    fun getAllData(): LiveData<List<Data>>

    @Insert
    fun insert(data: Data)

    @Query("DELETE FROM Data")
    fun deleteAll()
}