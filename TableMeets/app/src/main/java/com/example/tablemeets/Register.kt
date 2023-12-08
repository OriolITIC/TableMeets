package com.example.tablemeets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        val menuHelper = MenuHandler(this)
        val navigationHelper = NavigationHelper(this)
        val arrowBack = findViewById<ImageView>(R.id.left_arrow)
        val goToLogin = findViewById<Button>(R.id.button_save_register_data)

        goToLogin.setOnClickListener {
            navigationHelper.goToLogin()
        }

        arrowBack.setOnClickListener{
            navigationHelper.goToLogin()
        }
    }


}
