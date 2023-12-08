package com.example.tablemeets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class Settings: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)

        val menuHelper = MenuHandler(this)
        val navigationHelper = NavigationHelper(this)
        val menuIcon = findViewById<ImageView>(R.id.menu_logo)
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

    }
}