package com.mahan.compose.jetoutline.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mahan.compose.jetoutline.data.models.Server

@Database(entities = [Server::class], version = 1, exportSchema = false)
abstract class ServerDatabase: RoomDatabase() {
    abstract fun serverDao(): ServerDao
}