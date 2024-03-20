package com.example.tablemeets

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

class Login : AppCompatActivity() {

    private lateinit var appDatabaseHelper: AppDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val navigationHelper = NavigationHelper(this)

        val authenticationHelper = AuthenticationHelper(this)

        appDatabaseHelper = AppDatabaseHelper(this)

        //appDatabaseHelper.onCreate(appDatabaseHelper.writableDatabase)

        val goToAboutUs = findViewById<View>(R.id.about_us)
        val userNameInputLayout = findViewById<TextInputLayout>(R.id.username_input_text)
        val passwordInputLayout = findViewById<TextInputLayout>(R.id.password_input_text)
        val loginButton = findViewById<Button>(R.id.button_login)
        val goToRegisterButton = findViewById<Button>(R.id.button_register)
        val goToForgotPassword = findViewById<TextView>(R.id.forgot_password)
        val goToHome = findViewById<View>(R.id.menu_logo)

        goToHome.setOnClickListener{
            navigationHelper.goToHome()
        }

        goToAboutUs.setOnClickListener {
            navigationHelper.goToAboutUs()
        }

        goToRegisterButton.setOnClickListener {
            navigationHelper.goToRegister()
        }

        goToForgotPassword.setOnClickListener {
            navigationHelper.goToForgotPassword()
        }

        loginButton.setOnClickListener{
            val userName = userNameInputLayout.editText?.text.toString()
            val password = passwordInputLayout.editText?.text.toString()

            // Intenta iniciar sesión
            val isLoginSuccessful = authenticationHelper.loginUser(this, userName, password)

            // Verifica si el inicio de sesión fue exitoso
            if (isLoginSuccessful) {
                // Navega a la pantalla de inicio solo si el inicio de sesión es exitoso
                navigationHelper.goToHome()
            } else {
                // Muestra un mensaje de error en caso de que el inicio de sesión falle
                Toast.makeText(this, "Inicio de sesión fallido. Verifica tu nombre de usuario y contraseña.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}