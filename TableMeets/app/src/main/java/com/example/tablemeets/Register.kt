package com.example.tablemeets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        val menuHelper = MenuHandler(this)
        val navigationHelper = NavigationHelper(this)
        val userNameInputLayout = findViewById<TextInputLayout>(R.id.username_input_text)
        val emailInputLayout = findViewById<TextInputLayout>(R.id.email_input_text)
        val passwordInputLayout = findViewById<TextInputLayout>(R.id.password_input_text)
        val confirmPassword = findViewById<TextInputLayout>(R.id.confirmPassword)
        val buttonRegister = findViewById<Button>(R.id.button_save_register_data)


        val authenticationHelper = AuthenticationHelper(this)

        buttonRegister.setOnClickListener {
            val username = userNameInputLayout.editText?.text.toString()
            val email = emailInputLayout.editText?.text.toString()
            val password = passwordInputLayout.editText?.text.toString()
            val confirmPassword = confirmPassword.editText?.text.toString()

            if (username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {

                val isRegistered = authenticationHelper.registerUser(this, username, email, password, confirmPassword)
                if (isRegistered) {

                        navigationHelper.goToLogin()

                    // Usuario registrado exitosamente
                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()

                }
            } else {
                // Campos vacíos
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            }
        }

    }
}

