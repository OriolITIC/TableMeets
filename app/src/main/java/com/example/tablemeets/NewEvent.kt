package com.example.tablemeets

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

class NewEvent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_event)

        val menuHelper = MenuHandler(this)
        val navigationHelper = NavigationHelper(this)
        val menuIcon = findViewById<ImageView>(R.id.menu_icon)
        val homeLogo = findViewById<ImageView>(R.id.home_logo)
        val nameTextInput = findViewById<TextInputLayout>(R.id.NameTextInput)
        val gameTextInput = findViewById<TextInputLayout>(R.id.GameTextInput)
        val locationTextInput = findViewById<TextInputLayout>(R.id.LocationTextInput)
        val timeTextInput = findViewById<TextInputLayout>(R.id.TimeTextInput)
        val dateTextInput = findViewById<TextInputLayout>(R.id.DateTextInput)
        val descTextInput = findViewById<TextInputLayout>(R.id.DescTextInput)
        val removeData = findViewById<Button>(R.id.button_remove_all_inputs)
        val createEvent = findViewById<Button>(R.id.button_create_event)

        menuIcon.setOnClickListener {
            menuHelper.showPopupMenu(it, navigationHelper)
        }

        homeLogo.setOnClickListener {
            navigationHelper.goToHome()
        }

        removeData.setOnClickListener {
            nameTextInput.editText?.text?.clear()
            gameTextInput.editText?.text?.clear()
            locationTextInput.editText?.text?.clear()
            timeTextInput.editText?.text?.clear()
            dateTextInput.editText?.text?.clear()
            descTextInput.editText?.text?.clear()
        }

        createEvent.setOnClickListener {
            val name = nameTextInput.editText?.text.toString()
            val gameName = gameTextInput.editText?.text.toString()
            val location = locationTextInput.editText?.text.toString()
            val time = timeTextInput.editText?.text.toString()
            val date = dateTextInput.editText?.text.toString()
            val description = descTextInput.editText?.text.toString()

            if (name.isNotEmpty() && gameName.isNotEmpty() && location.isNotEmpty() &&
                time.isNotEmpty() && date.isNotEmpty() && description.isNotEmpty()) {

                val dbHelper = AppDatabaseHelper(this)
                val userId = getAuthenticatedUserId() // Obtener el ID del usuario autenticado
                dbHelper.createEvent(this, userId, name, gameName, location, date, time, description)

                // Notificar al usuario que el evento se creó exitosamente
                Toast.makeText(this, "¡Evento creado con éxito!", Toast.LENGTH_SHORT).show()

                // Limpiar los TextInputLayouts después de la inserción
                nameTextInput.editText?.text?.clear()
                gameTextInput.editText?.text?.clear()
                locationTextInput.editText?.text?.clear()
                timeTextInput.editText?.text?.clear()
                dateTextInput.editText?.text?.clear()
                descTextInput.editText?.text?.clear()

            } else {
                // Notificar al usuario que algunos campos están vacíos
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun getAuthenticatedUserId(): Long {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getLong("userId", -1)
    }
}