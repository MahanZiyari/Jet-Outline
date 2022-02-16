package com.mahan.compose.jetoutline.data.models

data class Server(
    val name: String = "My Server",
    val ip: String = "116.122.80.214",
    val port: String = "80",
    val connected: Boolean = false
)
