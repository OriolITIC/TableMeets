package com.example.tablemeets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class TypeOfEvent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.type_of_event)

        val menuHelper = MenuHelper(this)
        val menuIcon = findViewById<ImageView>(R.id.menu_icon)
        val goToEventsCreatedByMe = findViewById<Button>(R.id.button_events_created_by_me)
        val goToAttendingEvents = findViewById<Button>(R.id.button_attending_events)
        val arrowBack = findViewById<ImageView>(R.id.arrow_back)
        val homeLogo = findViewById<ImageView>(R.id.home_logo)


        arrowBack.setOnClickListener {
            menuHelper.goToHome()
        }

        homeLogo.setOnClickListener {
            menuHelper.goToHome()
        }

        menuIcon.setOnClickListener {
            menuHelper.showPopupMenu(it, menuHelper)
        }

        goToEventsCreatedByMe.setOnClickListener {

            menuHelper.goToEventsCreated()
        }

        goToAttendingEvents.setOnClickListener {

            menuHelper.goToAttendingEvents()
        }


    }
}
