package com.example.tablemeets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView

class ForgotPassword : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgot_password)


        val navigationHelper = NavigationHelper(this)
        val goToLogin = findViewById<Button>(R.id.button_send_email)

        goToLogin.setOnClickListener(){
            navigationHelper.goToLogin()
        }
    }

}
