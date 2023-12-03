package com.example.tablemeets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        // Asigna el botón desde tu diseño XML a una variable en Kotlin
        val botonIrATypeOfEvent = findViewById<Button>(R.id.button3)

        // Asigna un OnClickListener al botón para manejar su clic
        botonIrATypeOfEvent.setOnClickListener {
            irATypeOfEvent()
        }
    }

    private fun irATypeOfEvent() {
        val intent = Intent(this, TypeOfEvent::class.java)
        startActivity(intent)
    }
}