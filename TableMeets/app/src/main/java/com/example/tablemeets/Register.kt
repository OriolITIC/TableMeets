package com.example.tablemeets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        //val arrowBack = findViewById<ImageView>(R.id.arrow_back)
        val goToLogin = findViewById<Button>(R.id.button_save_register_data)

        goToLogin.setOnClickListener {

            goToLogin()
        }
    }

    private fun goToLogin() {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }
}
