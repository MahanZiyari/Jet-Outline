package com.mahan.compose.jetoutline.ui.screens.home

import androidx.compose.runtime.collectAsState
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
class HomeScreenViewModel @Inject constructor(private val repository: ServerRepository): ViewModel() {

    // States of BottomSheet TextField
    val accessKey = mutableStateOf("")
    fun onAccessKeyChange(newValue: String) {
        accessKey.value = newValue
    }

    // Severs List
    private val _servers = MutableStateFlow<List<Server>>(emptyList())
    val servers = _servers.asStateFlow()

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


}