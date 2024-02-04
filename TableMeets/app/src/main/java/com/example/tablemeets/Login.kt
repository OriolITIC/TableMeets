package com.example.tablemeets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)


        val navigationHelper = NavigationHelper(this)
        val goToAboutUs = findViewById<View>(R.id.about_us)
        val goToHome = findViewById<Button>(R.id.button_login)
        val goToRegister = findViewById<Button>(R.id.button_register)
        val goToForgotPassword = findViewById<TextView>(R.id.forgot_password)

        goToAboutUs.setOnClickListener {
            navigationHelper.goToAboutUs()
        }

        goToHome.setOnClickListener {
            navigationHelper.goToHome()
        }

        goToRegister.setOnClickListener {
            navigationHelper.goToRegister()
        }

        goToForgotPassword.setOnClickListener {
            navigationHelper.goToForgotPassword()
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

    private fun goToForgotPassword() {
        val intent = Intent(this, ForgotPassword::class.java)
        startActivity(intent)
    }

}