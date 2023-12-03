package com.example.tablemeets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class NewEvent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_event)

        val arrowBack = findViewById<ImageView>(R.id.arrow_back)
        val homeLogo = findViewById<ImageView>(R.id.home_logo)
        val createEvent = findViewById<ImageView>(R.id.home_logo)

        arrowBack.setOnClickListener {
            goToHome()
        }

        homeLogo.setOnClickListener {
            goToRegister()
        }




    }

    private fun goToHome() {
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
    }

    private fun goToRegister() {
        val intent = Intent(this, Register::class.java)
        startActivity(intent)
    }


}
