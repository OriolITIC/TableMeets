package com.example.tablemeets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tablemeets.controller.CreatedEventAdapter

class EventsCreated : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.events_created)

        val menuHelper = MenuHandler(this)
        val navigationHelper = NavigationHelper(this)
        val menuIcon = findViewById<ImageView>(R.id.menu_icon)
        val homeLogo = findViewById<ImageView>(R.id.home_logo)



        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewEventsCreated)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val dbHelper = AppDatabaseHelper(this)

        val eventList = dbHelper.getCreatedEvents()

        val adapter = CreatedEventAdapter(eventList)
        recyclerView.adapter = adapter

        homeLogo.setOnClickListener {
            navigationHelper.goToHome()
        }

        menuIcon.setOnClickListener {
            menuHelper.showPopupMenu(it, navigationHelper)
        }



    }
}




