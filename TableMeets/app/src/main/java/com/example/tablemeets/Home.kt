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


        val menuHelper = MenuHandler(this)
        val navigationHelper = NavigationHelper(this)
        val menuIcon = findViewById<ImageView>(R.id.menu_icon)
        val goToCreateEvent = findViewById<Button>(R.id.button_create_event)
        val goToTypeOfEvent = findViewById<Button>(R.id.button_my_events)
        val goToSearchEvent = findViewById<Button>(R.id.button_search_event)
        val goToGames = findViewById<Button>(R.id.button_games)


        menuIcon.setOnClickListener {
            menuHelper.showPopupMenu(it, navigationHelper)
        }

        goToCreateEvent.setOnClickListener {
            navigationHelper.goToCreateEvent()
        }

        goToTypeOfEvent.setOnClickListener {
            navigationHelper.goToTypeOfEvent()
        }

        goToSearchEvent.setOnClickListener {
            navigationHelper.goToSearchEvent()
        }

        goToGames.setOnClickListener {
            navigationHelper.goToGames()
        }
    }



    private fun goToGames() {
        val intent = Intent(this, Games::class.java)
        startActivity(intent)
    }
}