package com.example.tablemeets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)
        val menuHelper = MenuHelper(this)
        val menuIcon = findViewById<ImageView>(R.id.menu_icon)
        val goToCreateEvent = findViewById<Button>(R.id.button_create_event)
        val goToTypeOfEvent = findViewById<Button>(R.id.button_my_events)
        val goToSearchEvent = findViewById<Button>(R.id.button_search_event)
        val goToGames = findViewById<Button>(R.id.button_games)

        menuIcon.setOnClickListener {
            menuHelper.showPopupMenu(it, menuHelper)
        }

        goToCreateEvent.setOnClickListener {
            menuHelper.goToCreateEvent()
        }

        goToTypeOfEvent.setOnClickListener {
            menuHelper.goToTypeOfEvent()
        }

        goToSearchEvent.setOnClickListener {
            menuHelper.goToSearchEvent()
        }

        goToGames.setOnClickListener {
            menuHelper.goToGames()
        }
    }






    private fun goToGames() {
        val intent = Intent(this, Games::class.java)
        startActivity(intent)
    }



}