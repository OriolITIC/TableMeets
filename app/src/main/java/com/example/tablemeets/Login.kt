package com.example.tablemeets


import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
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

        fun tableExists(databaseHelper: SQLiteOpenHelper, tableName: String): Boolean {
            val db = databaseHelper.readableDatabase
            val cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='$tableName'", null)
            val exists = cursor.count > 0
            cursor.close()
            return exists
        }

        if (!tableExists(appDatabaseHelper, "Users")) {
            appDatabaseHelper.onCreate(appDatabaseHelper.writableDatabase)
        }


        val goToAboutUs = findViewById<View>(R.id.about_us)
        val userNameInputLayout = findViewById<TextInputLayout>(R.id.username_input_text)
        val passwordInputLayout = findViewById<TextInputLayout>(R.id.password_input_text)
        val loginButton = findViewById<Button>(R.id.button_login)
        val goToRegisterButton = findViewById<Button>(R.id.button_register)
        val goToForgotPassword = findViewById<TextView>(R.id.forgot_password)
        val goToHome = findViewById<View>(R.id.menu_logo)

        val reportTextView = findViewById<ImageView>(R.id.reportImageView)
        reportTextView.setOnClickListener {
            val intent = Intent(this, Report::class.java)
            startActivity(intent)
        }

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

            val isLoginSuccessful = authenticationHelper.loginUser(this, userName, password)

            if (isLoginSuccessful) {
                navigationHelper.goToHome()
            } else {
                Toast.makeText(this, "Inicio de sesión fallido. Verifica tu nombre de usuario y contraseña.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}