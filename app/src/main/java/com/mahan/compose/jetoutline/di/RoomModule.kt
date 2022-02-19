package com.mahan.compose.jetoutline.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mahan.compose.jetoutline.data.room.ServerDao
import com.mahan.compose.jetoutline.data.room.ServerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        ServerDatabase::class.java,
        "outline-database"
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: ServerDatabase): ServerDao = database.serverDao()
}