package com.example.tablemeets


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tablemeets.controller.AttendingEventAdapter
import com.example.tablemeets.controller.CreatedEventAdapter

class SearchEvent : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_event)

        val menuHelper = MenuHandler(this)
        val navigationHelper = NavigationHelper(this)
        val menuIcon = findViewById<ImageView>(R.id.menu_icon)
        val homeLogo = findViewById<ImageView>(R.id.home_logo)
        val searchEvent = findViewById<ImageView>(R.id.search_event)

        searchEvent.setOnClickListener {
            val dbHelper = AppDatabaseHelper(this)
            val eventList = dbHelper.getEvents()
            val recyclerView: RecyclerView = findViewById(R.id.recyclerViewEvents)
            recyclerView.layoutManager = LinearLayoutManager(this)
            val adapter = AttendingEventAdapter(eventList)
            recyclerView.adapter = adapter

        }

        menuIcon.setOnClickListener {
            menuHelper.showPopupMenu(it, navigationHelper)
        }

        homeLogo.setOnClickListener {
            navigationHelper.goToHome()
        }
    }
}
