package com.example.tablemeets

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import org.w3c.dom.Text

class AuthenticationHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 2
        private const val DATABASE_NAME = "TableMeetsDatabase"
        private const val TABLE_USERS = "Users"
        private const val COLUMN_USERNAME = "USERNAME"
        private const val COLUMN_EMAIL = "EMAIL"
        private const val COLUMN_PASSWORD = "PASSWORD"
    }


    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_USERS_TABLE = ("CREATE TABLE IF NOT EXISTS $TABLE_USERS ("
                + "$COLUMN_USERNAME TEXT PRIMARY KEY,"
                + "$COLUMN_EMAIL TEXT,"
                + "$COLUMN_PASSWORD TEXT"
                + ")")
        db.execSQL(CREATE_USERS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }

    fun registerUser(
        context: Context,
        username: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {

        if (checkUserExistence(username)) {
            //Toast.makeText(context, "El usuario ya está registrado", Toast.LENGTH_SHORT).show()
            Log.d("User Existence", "El usuario ya está registrado")
            return false
        }

        if (!isValidEmail(email)) {
            //Toast.makeText(context, "El correo electrónico no es válido", Toast.LENGTH_SHORT).show()
            Log.d("User Existence", "El correo electrónico no es válido")
            return false
        }

        if (!verifyPassword(context, password)) {
            return false
        }

        if (password != confirmPassword) {
            /*Toast.makeText(
                context,
                "La contraseña y la confirmación de la contraseña no coinciden",
                Toast.LENGTH_SHORT
            ).show()*/
            Log.d("User Existence", "La contraseña y la confirmación de la contraseña no coinciden")
            return false
        }

        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_USERNAME, username)
        values.put(COLUMN_EMAIL, email)
        values.put(COLUMN_PASSWORD, password)

        db.insert(TABLE_USERS, null, values)
        db.close()



        return true
    }

    fun loginUser(context: Context, username: String, password: String): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_USERS WHERE $COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?"
        val cursor = db.rawQuery(query, arrayOf(username, password))

        val result = cursor.count > 0

        if (result) {
            //Toast.makeText(context, "¡Inicio de sesión exitoso!", Toast.LENGTH_SHORT).show()

            cursor.moveToFirst()
            val userId = cursor.getLong(0)
            cursor.close()

            val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            with(sharedPreferences.edit()) {
                putLong("userId", userId)
                apply()
            }
        } else {
            /*Toast.makeText(
                context,
                "Nombre de usuario o contraseña incorrectos",
                Toast.LENGTH_SHORT
            ).show()*/
        }

        return result
    }

    fun isUserAuthenticated(): Boolean {
        val db = readableDatabase
        val query = "SELECT COUNT(*) FROM $TABLE_USERS"
        val cursor = db.rawQuery(query, null)
        cursor.moveToFirst()
        val count = cursor.getInt(0)
        cursor.close()
        return count > 0
    }

    fun checkUserExistence(username: String): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_USERS WHERE $COLUMN_USERNAME = ?"
        val cursor = db.rawQuery(query, arrayOf(username))

        val result = cursor.count > 0
        cursor.close()
        return result
    }

    fun verifyPassword(context: Context, password: String): Boolean {
        val regex = Regex("^(?=.*\\d{6,})(?=.*[a-z])(?=.*[A-Z])(?=.*[@\$!%*.?&]).{6,}\$")
        val hasDigit = password.any { it.isDigit() }
        val hasLowercase = password.any { it.isLowerCase() }
        val hasUppercase = password.any { it.isUpperCase() }
        val hasSpecialChar = password.any { it in "@\$!%*?&" }
        val containsAtLeastSixNumbers = password.count { it.isDigit() } >= 6


        if (!hasDigit) {
            /*Toast.makeText(
                context,
                "La contraseña debe contener al menos un dígito",
                Toast.LENGTH_SHORT
            ).show()*/
            Log.d("AuthenticationHelper", "La contraseña debe contener al menos un dígito")
        }

        if (!hasLowercase) {
            /*Toast.makeText(
                context,
                "La contraseña debe contener al menos una letra minúscula",
                Toast.LENGTH_SHORT
            ).show()*/
            Log.d("AuthenticationHelper", "La contraseña debe contener al menos una letra minúscula")
        }

        if (!hasUppercase) {
            /*Toast.makeText(
                context,
                "La contraseña debe contener al menos una letra mayúscula",
                Toast.LENGTH_SHORT
            ).show()*/
            Log.d("AuthenticationHelper", "La contraseña debe contener al menos una letra mayúscula")
        }

        if (!hasSpecialChar) {
            /*Toast.makeText(
                context,
                "La contraseña debe contener al menos un carácter especial (?=.*[@\$!%*.?&)",
                Toast.LENGTH_SHORT
            ).show()*/
            Log.d("AuthenticationHelper", "La contraseña debe contener al menos un carácter especial (?=.*[@\$!%*.?&)")
        }

        if (!containsAtLeastSixNumbers) {
            /*Toast.makeText(
                context,
                "La contraseña debe tener al menos 6 números",
                Toast.LENGTH_SHORT
            ).show()*/
            Log.d("AuthenticationHelper", "La contraseña debe tener al menos 6 números")
        }

        return regex.matches(password)
    }

    fun isValidEmail(email: String): Boolean {
        val regex = Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,})+\$")
        return regex.matches(email)
    }

    fun getAuthenticatedUsername(): String {
        val db = this.readableDatabase
        val query = "SELECT $COLUMN_USERNAME FROM $TABLE_USERS"
        val cursor = db.rawQuery(query, null)

        var username = ""

        cursor.use { cursor ->
            if (cursor != null && cursor.moveToFirst()) {
                val columnIndex = cursor.getColumnIndex(COLUMN_USERNAME)
                if (columnIndex != -1) {
                    username = cursor.getString(columnIndex)
                }
            }
        }

        cursor.close()
        db.close()

        return username
    }

    fun logout(context: Context) {
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            remove("userId")
            //Toast.makeText(context, "Removing userId", Toast.LENGTH_SHORT)
            apply()
        }
    }

}