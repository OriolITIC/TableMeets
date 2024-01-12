package com.example.tablemeets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
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

    }
}
