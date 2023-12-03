package com.example.tablemeets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class SearchEvent: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_event)

        val arrowBack = findViewById<ImageView>(R.id.arrow_back)
        val homeLogo = findViewById<ImageView>(R.id.home_logo)

        arrowBack.setOnClickListener {

            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        homeLogo.setOnClickListener {

            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

    }
}
