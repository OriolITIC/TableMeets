package com.example.tablemeets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class EventsCreated: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.events_created)

        val menuHelper = MenuHelper(this)
        val menuIcon = findViewById<ImageView>(R.id.menu_icon)
        val arrowBack = findViewById<ImageView>(R.id.arrow_back)
        val homeLogo = findViewById<ImageView>(R.id.home_logo)

        arrowBack.setOnClickListener {
            menuHelper.goToTypeOfEvent()
        }

        homeLogo.setOnClickListener {
            menuHelper.goToHome()
        }

        menuIcon.setOnClickListener {
            menuHelper.showPopupMenu(it, menuHelper)
        }

    }
}