package com.example.tablemeets

import MenuHandler
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView

class Games: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.games)


        val menuHelper = MenuHandler(this)
        val navigationHelper = NavigationHelper(this)
        val menuIcon = findViewById<ImageView>(R.id.menu_icon)
        val homeLogo = findViewById<ImageView>(R.id.home_logo)

        menuIcon.setOnClickListener {
            menuHelper.showPopupMenu(it, navigationHelper)
        }

        homeLogo.setOnClickListener {
            navigationHelper.goToHome()
        }
    }

}