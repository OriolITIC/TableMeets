package com.example.tablemeets.data

data class CreatedEvent(
    val id: Long,
    val gameName: String,
    val location: String,
    val date: String,
    val time: String,
    val description: String
)