package com.example.tablemeets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val goToHome = findViewById<Button>(R.id.button_login)
        val goToRegister = findViewById<Button>(R.id.button_register)
        val goToForgotPassword = findViewById<TextView>(R.id.forgot_password)


        goToHome.setOnClickListener {
            goToHome()
        }

        goToRegister.setOnClickListener {
            goToRegister()
        }

        goToForgotPassword.setOnClickListener {
            goToForgotPassword()
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