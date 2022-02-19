package com.mahan.compose.jetoutline.data.room

import androidx.room.*
import com.mahan.compose.jetoutline.data.models.Server
import kotlinx.coroutines.flow.Flow

@Dao
interface ServerDao {
    @Query("Select * from servers")
    fun getServers(): Flow<List<Server>>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(server: Server)

    @Delete
    suspend fun delete(server: Server)

    @Update
    suspend fun update(server: Server)
}