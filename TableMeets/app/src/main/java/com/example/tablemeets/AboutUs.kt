package com.example.tablemeets

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class AboutUs: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about_us)


        val navigationHelper = NavigationHelper(this)

    }
}