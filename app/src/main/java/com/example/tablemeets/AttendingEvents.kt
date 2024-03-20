package com.example.tablemeets

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tablemeets.controller.AttendingEventAdapter

class AttendingEvents : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.attending_events)

        val menuHelper = MenuHandler(this)
        val navigationHelper = NavigationHelper(this)
        val menuIcon = findViewById<ImageView>(R.id.menu_icon)
        val homeLogo = findViewById<ImageView>(R.id.home_logo)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewAttendingEvents)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val dbHelper = AppDatabaseHelper(this)

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getLong("userId", -1)

        if (userId != -1L) {
            val eventList = dbHelper.getAttendingEvents(userId)
            val adapter = AttendingEventAdapter(eventList)
            recyclerView.adapter = adapter
            Toast.makeText(this, "ID de usuario: $userId", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "No hay usuario autenticado", Toast.LENGTH_SHORT).show()
        }

        homeLogo.setOnClickListener {
            navigationHelper.goToHome()
        }

        menuIcon.setOnClickListener {
            menuHelper.showPopupMenu(it, navigationHelper)
        }
    }
}