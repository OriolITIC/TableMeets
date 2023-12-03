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

        val goToEventsCreatedByMe = findViewById<Button>(R.id.button_events_created_by_me)
        val goToAttendingEvents = findViewById<Button>(R.id.button_attending_events)
        val arrowBack = findViewById<ImageView>(R.id.arrow_back)
        val homeLogo = findViewById<ImageView>(R.id.home_logo)

        arrowBack.setOnClickListener {

            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        homeLogo.setOnClickListener {

            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        goToEventsCreatedByMe.setOnClickListener {

            val intent = Intent(this, EventsCreatedByMe::class.java)
            startActivity(intent)
        }

        goToAttendingEvents.setOnClickListener {

            val intent = Intent(this, AttendingEvents::class.java)
            startActivity(intent)
        }


    }
}
