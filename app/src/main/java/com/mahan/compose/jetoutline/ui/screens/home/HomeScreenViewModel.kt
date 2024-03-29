package com.mahan.compose.jetoutline.ui.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahan.compose.jetoutline.data.models.Server
import com.mahan.compose.jetoutline.data.room.ServerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val repository: ServerRepository) :
    ViewModel() {

    // States of BottomSheet TextField
    val accessKey = mutableStateOf("")
    fun onAccessKeyChange(newValue: String) {
        accessKey.value = newValue
    }

    // Severs List
    private val _servers = MutableStateFlow<List<Server>>(emptyList())
    val servers = _servers.asStateFlow()

    // Active Server
    val activeServer: MutableState<Server?> = mutableStateOf(null)

    // AlertDialogText
    val newName = mutableStateOf("")

    init {
        getServers()
    }

    private fun getServers() {
        viewModelScope.launch {
            repository.getServers().collect {
                _servers.value = it
            }
        }
    }

    fun connect(server: Server) {
        disconnectAllServers()
        viewModelScope.launch {
            repository.update(server)
        }
    }

    fun disconnect() {
        disconnectAllServers()
    }

    fun removeServer(server: Server) {
        viewModelScope.launch { repository.delete(server) }
    }

    fun renameServer(server: Server) {
        viewModelScope.launch {
            repository.update(
                Server(
                    id = server.id,
                    name = newName.value,
                    ip = server.ip,
                    port = server.port,
                    connected = server.connected
                )
            )
        }
        newName.value = ""
    }

    private fun disconnectAllServers() {
        _servers.value.filter { it.connected }.forEach {
            // Creating a server object for each connected server
            val tempServer = Server(
                id = it.id,
                name = it.name,
                ip = it.ip,
                port = it.port,
                connected = false
            )
            viewModelScope.launch {
                repository.update(tempServer)
            }
        }
    }
}