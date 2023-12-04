package com.example.tablemeets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class NewEvent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_event)

        val menuHelper = MenuHandler(this)
        val navigationHelper = NavigationHelper(this)
        val menuIcon = findViewById<ImageView>(R.id.menu_icon)
        val arrowBack = findViewById<ImageView>(R.id.arrow_back)
        val homeLogo = findViewById<ImageView>(R.id.home_logo)
        //val createEvent = findViewById<ImageView>(R.id.home_logo)

        menuIcon.setOnClickListener {
            menuHelper.showPopupMenu(it, navigationHelper)
        }

        arrowBack.setOnClickListener {
            navigationHelper.goToHome()
        }

        homeLogo.setOnClickListener {
            navigationHelper.goToHome()
        }

        arrowBack.setOnClickListener {
            navigationHelper.goToHome()
        }




    }




}
