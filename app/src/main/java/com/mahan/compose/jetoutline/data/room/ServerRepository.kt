package com.mahan.compose.jetoutline.data.room

import com.mahan.compose.jetoutline.data.models.Server
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class ServerRepository @Inject constructor(private val dao: ServerDao) {

    suspend fun insert(server: Server) = dao.insert(server)
    suspend fun delete(server: Server) = dao.delete(server)
    suspend fun update(server: Server) = dao.update(server)

    fun getServers() = dao.getServers()
}