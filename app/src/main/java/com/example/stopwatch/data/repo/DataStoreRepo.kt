package com.example.stopwatch.data.repo

import com.example.stopwatch.data.DataStore
import javax.inject.Inject

class DataStoreRepo @Inject constructor(
    private val getDataStore: DataStore
) {

    fun getTheme() = getDataStore.getAppTheme()

    suspend fun updateTheme(theme: String) = getDataStore.updateAppTheme(theme)

}