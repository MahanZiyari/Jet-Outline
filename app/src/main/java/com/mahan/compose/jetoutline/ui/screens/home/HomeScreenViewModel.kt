package com.mahan.compose.jetoutline.ui.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class HomeScreenViewModel: ViewModel() {
    val accessKey = mutableStateOf("")

    fun onAccessKeyChange(newValue: String) {
        accessKey.value = newValue
    }


}