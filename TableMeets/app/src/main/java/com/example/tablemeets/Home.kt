package com.example.tablemeets

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.view.menu.MenuPopupHelper

public class Home : AppCompatActivity(), MenuClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        val menuIcon = findViewById<ImageView>(R.id.menu_icon)
        val goToCreateEvent = findViewById<Button>(R.id.button_create_event)
        val goToTypeOfEvent = findViewById<Button>(R.id.button_my_events)
        val goToSearchEvent = findViewById<Button>(R.id.button_search_event)
        val goToGames = findViewById<Button>(R.id.button_games)

        menuIcon.setOnClickListener {
            menuIcon.setOnClickListener {
                MenuHelper.showPopupMenu(this, it, this)
            }
        }


        goToCreateEvent.setOnClickListener {
            goToCreateEvent()
        }

        goToTypeOfEvent.setOnClickListener {
            goToTypeOfEvent()
        }

        goToSearchEvent.setOnClickListener {
            goToSearchEvent()
        }

        goToGames.setOnClickListener {
            goToGames()
        }
    }







    override fun goToCreateEvent() {
        val intent = Intent(this, NewEvent::class.java)
        startActivity(intent)
    }

    private fun goToTypeOfEvent() {
        val intent = Intent(this, TypeOfEvent::class.java)
        startActivity(intent)
    }

    private fun goToSearchEvent() {
        val intent = Intent(this, SearchEvent::class.java)
        startActivity(intent)
    }

    private fun goToGames() {
        val intent = Intent(this, Games::class.java)
        startActivity(intent)
    }



}