package com.example.tablemeets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class TypeOfEvent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.type_of_event)

        val menuHelper = MenuHandler(this)
        val menuIcon = findViewById<ImageView>(R.id.menu_logo)
        val navigationHelper = NavigationHelper(this)
        val goToEventsCreatedByMe = findViewById<Button>(R.id.button_events_created_by_me)
        val goToAttendingEvents = findViewById<Button>(R.id.button_attending_events)
        val arrowBack = findViewById<ImageView>(R.id.left_arrow)
        val homeLogo = findViewById<ImageView>(R.id.home_logo)


        arrowBack.setOnClickListener {
            navigationHelper.goToHome()
        }

        homeLogo.setOnClickListener {
            navigationHelper.goToHome()
        }

        menuIcon.setOnClickListener {
            menuHelper.showPopupMenu(it, navigationHelper)
        }

        goToEventsCreatedByMe.setOnClickListener {

            navigationHelper.goToEventsCreated()
        }

        goToAttendingEvents.setOnClickListener {

            navigationHelper.goToAttendingEvents()
        }


    }
}
