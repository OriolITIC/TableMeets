package com.example.tablemeets.data

data class Event(
    val eventId: Long,
    val userId: Long,
    val eventName: String,
    val gameName: String,
    val location: String,
    val eventDate: String,
    val eventTime: String,
    val description: String
)