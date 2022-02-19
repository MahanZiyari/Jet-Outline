package com.mahan.compose.jetoutline.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "servers")
data class Server(
    @PrimaryKey(autoGenerate = true)
    val id: Int = -1,
    val name: String = "My Server",
    val ip: String = "116.122.80.214",
    val port: String = "80",
    val connected: Boolean = false
)
