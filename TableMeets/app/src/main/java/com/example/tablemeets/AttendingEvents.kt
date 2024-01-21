package com.example.tablemeets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tablemeets.controller.AttendingEventAdapter

class AttendingEvents: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.attending_events)

        val menuHelper = MenuHandler(this)
        val navigationHelper = NavigationHelper(this)
        val menuIcon = findViewById<ImageView>(R.id.menu_icon)
        val arrowBack = findViewById<ImageView>(R.id.left_arrow)
        val homeLogo = findViewById<ImageView>(R.id.home_logo)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewAttendingEvents)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Supongamos que tienes una lista de eventos a los que te has unido
        val attendingEventList = listOf("Evento X", "Evento Y", "Evento Z", "Evento W", "Evento V",
            "Evento K", "Evento D", "Evento B", "Evento E", "Evento J")

        val adapter = AttendingEventAdapter(attendingEventList)
        recyclerView.adapter = adapter

        arrowBack.setOnClickListener {
            navigationHelper.goToTypeOfEvent()
        }

        homeLogo.setOnClickListener {
            navigationHelper.goToHome()
        }

        menuIcon.setOnClickListener {
            menuHelper.showPopupMenu(it, navigationHelper)
        }

    }
}