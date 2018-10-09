package com.yuricfurusho.yurigiphyapi

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.yuricfurusho.yurigiphyapi.model.Data
import com.yuricfurusho.yurigiphyapi.model.DataRepository
import com.yuricfurusho.yurigiphyapi.model.DataRoomDatabase
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import kotlin.coroutines.experimental.CoroutineContext

class GiphyViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: DataRepository
    val allData: LiveData<List<Data>>

    private var parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main

    private val scope = CoroutineScope(coroutineContext)


    init {
        val dataDao = DataRoomDatabase.getDatabase(application).dataDao()
        repository = DataRepository(dataDao)
        allData = repository.allData
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

    fun insert(data: Data) = scope.launch(Dispatchers.IO) {
        repository.insert(data)
    }
}