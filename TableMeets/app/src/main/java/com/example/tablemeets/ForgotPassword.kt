package com.example.tablemeets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class ForgotPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgot_password)

        //val arrowBack = findViewById<ImageView>(R.id.arrow_back)
        val navigationHelper = NavigationHelper(this)
        val goToLogin = findViewById<Button>(R.id.button_send_email)

        //arrowBack.setOnClickListener {
            //navigationHelper.goToLogin()
        //}

        goToLogin.setOnClickListener(){
            navigationHelper.goToLogin()
        }
    }


}
