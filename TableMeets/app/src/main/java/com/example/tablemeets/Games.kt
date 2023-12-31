package com.example.tablemeets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class Games: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.games)

        val menuHelper = MenuHandler(this)
        val navigationHelper = NavigationHelper(this)
        val menuIcon = findViewById<ImageView>(R.id.menu_icon)
        val arrowBack = findViewById<ImageView>(R.id.left_arrow)
        val homeLogo = findViewById<ImageView>(R.id.home_logo)

        menuIcon.setOnClickListener {
            menuHelper.showPopupMenu(it, navigationHelper)
        }

        arrowBack.setOnClickListener {
            navigationHelper.goToHome()
        }

        homeLogo.setOnClickListener {
            navigationHelper.goToHome()
        }
    }
}