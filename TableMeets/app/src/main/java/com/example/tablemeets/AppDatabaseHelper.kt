package com.example.tablemeets

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.tablemeets.data.AttendingEvent
import com.example.tablemeets.data.CreatedEvent

class AppDatabaseHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME,
    null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "TableMeetsAppDatabase"
        const val DATABASE_VERSION = 1
    }

    private val tableUsers = "CREATE TABLE Users (" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "USERNAME TEXT," +
            "EMAIL TEXT," +
            "PASSWORD TEXT)"


    private val tableCreatedEvents = "CREATE TABLE CreatedEvents (" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "EVENT_NAME TEXT," +
            "GAME_NAME TEXT," +
            "LOCATION TEXT," +
            "DATE TEXT," +
            "TIME TEXT," +
            "DESCRIPTION TEXT)"

    private val tableAttendingEvents = "CREATE TABLE AttendingEvents (" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "USER_ID INTEGER," +
            "GAME_NAME TEXT," +
            "LOCATION TEXT," +
            "DATE TEXT," +
            "TIME TEXT," +
            "DESCRIPTION TEXT," +
            "FOREIGN KEY(USER_ID) REFERENCES Users(ID))"

    override fun onCreate(database: SQLiteDatabase?) {
        database?.execSQL(tableUsers)
        database?.execSQL(tableCreatedEvents)
        database?.execSQL(tableAttendingEvents)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun addCreatedEvent(eventName: String, gameName: String, location: String, date: String,
                        time: String, description: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("EVENT_NAME",eventName)
            put("GAME_NAME", gameName)
            put("LOCATION", location)
            put("DATE", date)
            put("TIME", time)
            put("DESCRIPTION", description)
        }
        return db.insert("CreatedEvents", null, values)
    }

    fun addAttendingEvent(id: Int, idUsuario: Long, eventName: String, gameName: String,
                          location: String, fecha: String, hora: String,
                          description: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("ID", id)
            put("USER_ID", idUsuario)
            put("EVENT_NAME",eventName)
            put("GAME_NAME", gameName)
            put("LOCATION", location)
            put("DATE", fecha)
            put("TIME", hora)
            put("DESCRIPTION", description)

        }
        return db.insert("AttendingEvents", null, values)
    }

    fun getCreatedEvents(): List<CreatedEvent> {
        val events = mutableListOf<CreatedEvent>()
        val db = readableDatabase

        db.query("CreatedEvents", null, null, null, null,
            null, null).use { cursor ->
            val idIndex = cursor.getColumnIndex("ID")
            val eventNameIndex = cursor.getColumnIndex("EVENT_NAME")
            val gameNameIndex = cursor.getColumnIndex("GAME_NAME")
            val locationIndex = cursor.getColumnIndex("LOCATION")
            val dateIndex = cursor.getColumnIndex("DATE")
            val timeIndex = cursor.getColumnIndex("TIME")
            val descriptionIndex = cursor.getColumnIndex("DESCRIPTION")

            while (cursor.moveToNext()) {
                val id = if (idIndex >= 0) cursor.getLong(idIndex) else -1
                val eventName = if (eventNameIndex >= 0) cursor.getString(eventNameIndex) else ""
                val gameName = if (gameNameIndex >= 0) cursor.getString(gameNameIndex) else ""
                val location = if (locationIndex >= 0) cursor.getString(locationIndex) else ""
                val date = if (dateIndex >= 0) cursor.getString(dateIndex) else ""
                val time = if (timeIndex >= 0) cursor.getString(timeIndex) else ""
                val description = if (descriptionIndex >= 0) cursor.getString(descriptionIndex) else ""


                val createdEvent = CreatedEvent(id,eventName, gameName, location, date, time, description)
                events.add(createdEvent)
            }
        }

        return events
    }

    fun getAttendingEvents(): List<AttendingEvent> {
        val events = mutableListOf<AttendingEvent>()
        val db = readableDatabase

        db.query("AttendingEvents", null, null, null, null,
            null, null).use { cursor ->
            val idIndex = cursor.getColumnIndex("ID")
            val eventNameIndex = cursor.getColumnIndex("EVENT_NAME") // Asegúrate de que esta columna exista en tu tabla
            val gameNameIndex = cursor.getColumnIndex("GAME_NAME")
            val locationIndex = cursor.getColumnIndex("LOCATION")
            val dateIndex = cursor.getColumnIndex("DATE")
            val timeIndex = cursor.getColumnIndex("TIME")
            val descriptionIndex = cursor.getColumnIndex("DESCRIPTION")
            val userIdIndex = cursor.getColumnIndex("USER_ID")

            while (cursor.moveToNext()) {
                val id = if (idIndex >= 0) cursor.getLong(idIndex) else -1
                val eventName = if (eventNameIndex >= 0) cursor.getString(eventNameIndex) else "" // Obtener el valor de la columna EVENT_NAME
                val gameName = if (gameNameIndex >= 0) cursor.getString(gameNameIndex) else ""
                val location = if (locationIndex >= 0) cursor.getString(locationIndex) else ""
                val date = if (dateIndex >= 0) cursor.getString(dateIndex) else ""
                val time = if (timeIndex >= 0) cursor.getString(timeIndex) else ""
                val description = if (descriptionIndex >= 0) cursor.getString(descriptionIndex) else ""
                val userId = if (userIdIndex >= 0) cursor.getLong(userIdIndex) else -1

                val event = AttendingEvent(id, eventName, gameName, location, date, time, description, userId)
                events.add(event)
            }
        }
        return events
    }



    fun deleteCreatedEvent(eventId: Long): Int {
        val db = writableDatabase
        return db.delete("CreatedEvents", "ID = ?", arrayOf(eventId.toString()))
    }


    fun loadDataFromDatabase() {
        val createdEvents = getCreatedEvents()
        for (event in createdEvents) {
            println("Event ID: ${event.id}, Name: ${event.gameName}, Location: ${event.location}," +
                    " Date: ${event.date}, Time: ${event.time}, Description: ${event.description}")
        }
    }
}
