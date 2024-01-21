package com.example.tablemeets

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.tablemeets.data.CreatedEvent

class AppDatabaseHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

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
            "GAME_NAME TEXT," +
            "LOCATION TEXT," +
            "DATE TEXT," +
            "TIME TEXT," +
            "DESCRIPTION TEXT)"

    private val tableAttendingEvents = "CREATE TABLE AttendingEvents (" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "GAME_NAME TEXT," +
            "LOCATION TEXT," +
            "DATE TEXT," +
            "TIME TEXT," +
            "DESCRIPTION TEXT," +
            "USER_ID INTEGER," +
            "FOREIGN KEY(USER_ID) REFERENCES Users(ID))"

    override fun onCreate(database: SQLiteDatabase?) {
        database?.execSQL(tableUsers)
        database?.execSQL(tableCreatedEvents)
        database?.execSQL(tableAttendingEvents)

        // Puedes agregar inicialización de eventos aquí si es necesario
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Manejar actualizaciones de la base de datos si es necesario
    }

    fun addCreatedEvent(gameName: String, location: String, date: String, time: String, description: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("GAME_NAME", gameName)
            put("LOCATION", location)
            put("DATE", date)
            put("TIME", time)
            put("DESCRIPTION", description)
        }
        return db.insert("CreatedEvents", null, values)
    }

    fun addAttendingEvent(id: Int, gameName: String, location: String, fecha: String, hora: String, descripcion: String, idUsuario: Long): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("ID", id)
            put("GAME_NAME", gameName)
            put("LOCATION", location)
            put("DATE", fecha)
            put("TIME", hora)
            put("DESCRIPTION", descripcion)
            put("USER_ID", idUsuario)
        }
        return db.insert("AttendingEvents", null, values)
    }

    fun getCreatedEvents(): List<CreatedEvent> {
        val events = mutableListOf<CreatedEvent>()
        val db = readableDatabase

        db.query("CreatedEvents", null, null, null, null, null, null).use { cursor ->
            val idIndex = cursor.getColumnIndex("ID")
            val gameNameIndex = cursor.getColumnIndex("GAME_NAME")
            val locationIndex = cursor.getColumnIndex("LOCATION")
            val dateIndex = cursor.getColumnIndex("DATE")
            val timeIndex = cursor.getColumnIndex("TIME")
            val descriptionIndex = cursor.getColumnIndex("DESCRIPTION")

            while (cursor.moveToNext()) {
                val id = if (idIndex >= 0) cursor.getLong(idIndex) else -1
                val gameName = if (gameNameIndex >= 0) cursor.getString(gameNameIndex) else ""
                val location = if (locationIndex >= 0) cursor.getString(locationIndex) else ""
                val date = if (dateIndex >= 0) cursor.getString(dateIndex) else ""
                val time = if (timeIndex >= 0) cursor.getString(timeIndex) else ""
                val description = if (descriptionIndex >= 0) cursor.getString(descriptionIndex) else ""

                val createdEvent = CreatedEvent(id, gameName, location, date, time, description)
                events.add(createdEvent)
            }
        }

        return events
    }

    fun loadDataFromDatabase() {
        val createdEvents = getCreatedEvents()

        // Haz algo con los eventos creados, por ejemplo, muestra la información en la consola
        for (event in createdEvents) {
            println("Event ID: ${event.id}, Name: ${event.gameName}, Location: ${event.location}, Date: ${event.date}, Time: ${event.time}, Description: ${event.description}")
        }
    }



}
