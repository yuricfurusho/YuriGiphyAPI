package com.yuricfurusho.yurigiphyapi.model

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread

/**
 * Created by Yuri Furusho on 28/09/18.
 */
class DataRepository(private val dao: DataDao) {

    val allData: LiveData<MutableList<Data>> = dao.getAllData()

    @WorkerThread
    suspend fun insert(data: Data) {
        dao.insert(data)
    }

}