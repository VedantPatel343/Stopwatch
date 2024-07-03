package com.example.stopwatch.di

import android.content.Context
import com.example.stopwatch.data.DataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun getDataStore(@ApplicationContext context: Context) : DataStore =
        DataStore(context)

}