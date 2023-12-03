package com.example.tablemeets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class Profile: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        val menuHelper = MenuHelper(this)
        val menuIcon = findViewById<ImageView>(R.id.menu_icon)
        val arrowBack = findViewById<ImageView>(R.id.arrow_back)
        val homeLogo = findViewById<ImageView>(R.id.home_logo)
        val editProfile = findViewById<Button>(R.id.button_edit_profile)

        arrowBack.setOnClickListener {
            menuHelper.goToHome()
        }

        homeLogo.setOnClickListener {
            menuHelper.goToHome()
        }

        menuIcon.setOnClickListener {
            menuHelper.showPopupMenu(it, menuHelper)
        }

        editProfile.setOnClickListener {
            menuHelper.goToEditProfile()
        }

    }
}