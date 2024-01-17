package com.example.tablemeets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class SearchEvent: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_event)


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
