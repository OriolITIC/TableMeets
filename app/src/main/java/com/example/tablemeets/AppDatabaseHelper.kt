package com.example.tablemeets

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import com.example.tablemeets.data.Event


class AppDatabaseHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME,
    null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "TableMeetsDatabase"
        const val DATABASE_VERSION = 2
    }

    private val authenticationHelper: AuthenticationHelper

    init {
        authenticationHelper = AuthenticationHelper(context)
    }

    val tableUsers = "CREATE TABLE Users (" +
            "USER_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "USERNAME TEXT," +
            "EMAIL TEXT," +
            "PASSWORD TEXT)"

    val tableEvents = "CREATE TABLE Events (" +
            "EVENT_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "USER_ID INTEGER," +
            "EVENT_NAME TEXT," +
            "GAME_NAME INTEGER," +
            "LOCATION TEXT," +
            "EVENT_DATE TEXT," +
            "EVENT_TIME TEXT," +
            "DESCRIPTION TEXT," +
            "FOREIGN KEY(USER_ID) REFERENCES Users(USER_ID))"

    val tableUserEvents = "CREATE TABLE UserEvent (" +
            "USER_ID INTEGER," +
            "EVENT_ID INTEGER," +
            "FOREIGN KEY(USER_ID) REFERENCES Users(USER_ID)," +
            "FOREIGN KEY(EVENT_ID) REFERENCES Events(EVENT_ID)," +
            "PRIMARY KEY (USER_ID, EVENT_ID))"

    val tableGames = "CREATE TABLE Games (" +
            "GAME_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "Category TEXT," +
            "Description TEXT," +
            "Rules TEXT," +
            "FOREIGN KEY(GAME_ID) REFERENCES Events(GAME_ID))"

    override fun onCreate(database: SQLiteDatabase?) {
        Log.d("AppDatabaseHelper", "onCreate() called")

        database?.execSQL(tableUsers)
        database?.execSQL(tableEvents)
        database?.execSQL(tableGames)
        database?.execSQL(tableUserEvents)

        Log.d("AppDatabaseHelper", "Tables created successfully")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    fun createEvent(context: Context, userId: Long, eventName: String, gameName: String, location: String, eventDate: String,
                    eventTime: String, description: String): Long {
        val db = writableDatabase
        var eventId: Long = -1

        db.beginTransaction()
        try {
            val values = ContentValues().apply {
                put("USER_ID", userId)
                put("EVENT_NAME", eventName)
                put("GAME_NAME", gameName)
                put("LOCATION", location)
                put("EVENT_DATE", eventDate)
                put("EVENT_TIME", eventTime)
                put("DESCRIPTION", description)
            }
            eventId = db.insert("Events", null, values)

            if (eventId != -1L) {
                val userEventValues = ContentValues().apply {
                    put("USER_ID", userId)
                    put("EVENT_ID", eventId)
                }
                db.insert("UserEvent", null, userEventValues)

                db.setTransactionSuccessful()
            }
        } catch (e: Exception) {
            Log.e("AppDatabaseHelper", "Error al insertar evento: ${e.message}")
        } finally {
            db.endTransaction()
        }

        if (eventId != -1L) {
            val message = "Evento creado:\n" +
                    "Nombre: $eventName\n" +
                    "Juego: $gameName\n" +
                    "Ubicación: $location\n" +
                    "Fecha: $eventDate\n" +
                    "Hora: $eventTime\n" +
                    "Descripción: $description"

            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Hubo un problema al crear el evento", Toast.LENGTH_SHORT).show()
        }

        return eventId
    }


    fun getEvents(): List<Event> {
        val events = mutableListOf<Event>()
        val db = readableDatabase

        db.query("Events", null, null, null, null, null, null)
            .use { cursor ->
                val eventIdIndex = cursor.getColumnIndex("EVENT_ID")
                val userIdIndex = cursor.getColumnIndex("USER_ID")
                val eventNameIndex = cursor.getColumnIndex("EVENT_NAME")
                val gameNameIndex = cursor.getColumnIndex("GAME_NAME")
                val locationIndex = cursor.getColumnIndex("LOCATION")
                val dateIndex = cursor.getColumnIndex("EVENT_DATE")
                val timeIndex = cursor.getColumnIndex("EVENT_TIME")
                val descriptionIndex = cursor.getColumnIndex("DESCRIPTION")

                while (cursor.moveToNext()) {
                    val eventId = cursor.getLong(eventIdIndex)
                    val userId = cursor.getLong(userIdIndex)
                    val eventName = cursor.getString(eventNameIndex)
                    val gameName = cursor.getString(gameNameIndex)
                    val location = cursor.getString(locationIndex)
                    val date = cursor.getString(dateIndex)
                    val time = cursor.getString(timeIndex)
                    val description = cursor.getString(descriptionIndex)

                    val event = Event(eventId, userId, eventName, gameName, location, date, time, description)
                    events.add(event)
                }
            }
        return events
    }


    fun getEventsByUserId(userId: Long): List<Event> {
        if (!authenticationHelper.isUserAuthenticated()) {
            return emptyList()
        }

        val events = mutableListOf<Event>()
        val db = readableDatabase

        val selection = "USER_ID = ?"
        val selectionArgs = arrayOf(userId.toString())

        db.query("Events", null, selection, selectionArgs, null, null, null)
            .use { cursor ->
                val eventIdIndex = cursor.getColumnIndex("EVENT_ID")
                val userIdIndex = cursor.getColumnIndex("USER_ID")
                val eventNameIndex = cursor.getColumnIndex("EVENT_NAME")
                val gameNameIndex = cursor.getColumnIndex("GAME_NAME")
                val locationIndex = cursor.getColumnIndex("LOCATION")
                val dateIndex = cursor.getColumnIndex("EVENT_DATE")
                val timeIndex = cursor.getColumnIndex("EVENT_TIME")
                val descriptionIndex = cursor.getColumnIndex("DESCRIPTION")

                while (cursor.moveToNext()) {
                    val eventId = cursor.getLong(eventIdIndex)
                    val userId = cursor.getLong(userIdIndex)
                    val eventName = cursor.getString(eventNameIndex)
                    val gameName = cursor.getString(gameNameIndex)
                    val location = cursor.getString(locationIndex)
                    val date = cursor.getString(dateIndex)
                    val time = cursor.getString(timeIndex)
                    val description = cursor.getString(descriptionIndex)

                    val event = Event(eventId, userId, eventName, gameName, location, date, time, description)
                    events.add(event)
                }
            }
        return events
    }

    fun getAttendingEvents(userId: Long): List<Event> {
        val events = mutableListOf<Event>()
        val db = readableDatabase

        val query = """
        SELECT * FROM Events
        INNER JOIN UserEvent ON Events.EVENT_ID = UserEvent.EVENT_ID
        WHERE UserEvent.USER_ID = ? AND Events.USER_ID != ?
    """.trimIndent()

        val selectionArgs = arrayOf(userId.toString(), userId.toString())

        db.rawQuery(query, selectionArgs)?.use { cursor ->
            val eventIdIndex = cursor.getColumnIndex("EVENT_ID")
            val eventNameIndex = cursor.getColumnIndex("EVENT_NAME")
            val gameNameIndex = cursor.getColumnIndex("GAME_NAME")
            val locationIndex = cursor.getColumnIndex("LOCATION")
            val dateIndex = cursor.getColumnIndex("EVENT_DATE")
            val timeIndex = cursor.getColumnIndex("EVENT_TIME")
            val descriptionIndex = cursor.getColumnIndex("DESCRIPTION")

            while (cursor.moveToNext()) {
                val eventId = cursor.getLong(eventIdIndex)
                val eventName = cursor.getString(eventNameIndex)
                val gameName = cursor.getString(gameNameIndex)
                val location = cursor.getString(locationIndex)
                val date = cursor.getString(dateIndex)
                val time = cursor.getString(timeIndex)
                val description = cursor.getString(descriptionIndex)

                val event = Event(eventId, userId, eventName, gameName, location, date, time, description)
                events.add(event)
            }
        }

        return events
    }



}
