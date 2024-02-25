package com.example.tablemeets

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        val navigationHelper = NavigationHelper(this)
        val menuIcon = findViewById<ImageView>(R.id.menu_icon)
        val goToCreateEvent = findViewById<Button>(R.id.button_create_event)
        val goToTypeOfEvent = findViewById<Button>(R.id.button_my_events)
        val goToSearchEvent = findViewById<Button>(R.id.button_search_event)
        val goToGames = findViewById<Button>(R.id.button_games)
        val helloUser = findViewById<TextView>(R.id.helloUser)

        val authenticationHelper = AuthenticationHelper(this)
        val username = authenticationHelper.getAuthenticatedUsername()


        if (!username.isNullOrEmpty()) {

            helloUser.text = "¡Hola $username!"
        } else {

            helloUser.text = "¡Hola!"
        }

        menuIcon.setOnClickListener {

            showMenu()
        }

        goToCreateEvent.setOnClickListener {
            navigationHelper.goToCreateEvent()
        }

        goToTypeOfEvent.setOnClickListener {
            navigationHelper.goToTypeOfEvent()
        }

        goToSearchEvent.setOnClickListener {
            navigationHelper.goToSearchEvent()
        }

        goToGames.setOnClickListener {
            navigationHelper.goToGames()
        }
    }

    private fun showMenu() {
        // Inflar el diseño menu_layout.xml
        val menuView = layoutInflater.inflate(R.layout.menu_layout, null)

        // Obtener una referencia al DrawerLayout desde el menú inflado
        val drawerLayout = menuView.findViewById<DrawerLayout>(R.id.drawer_layout)

        if (drawerLayout == null) {
            Log.e("DrawerLayout", "El DrawerLayout es nulo.")
            return
        }

        // Si llegamos aquí, significa que el DrawerLayout se ha encontrado correctamente
        Log.d("DrawerLayout", "DrawerLayout encontrado correctamente.")

        // Obtener una referencia al Toolbar desde el menú inflado
        val toolbar = menuView.findViewById<Toolbar>(R.id.toolbar)

        // Configurar ActionBarDrawerToggle con el DrawerLayout y el Toolbar
        val drawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        // Agregar el DrawerListener y sincronizar el estado del toggle
        try {
            drawerLayout.addDrawerListener(drawerToggle)
            drawerToggle.syncState()
            Log.d("DrawerLayout", "DrawerLayout inflado.")
        } catch (e: Exception) {
            Log.e("ERROR", "Error al agregar DrawerListener o sincronizar el estado del toggle: ${e.message}")
        }

        // Abrir el cajón de navegación
        drawerLayout.openDrawer(GravityCompat.START)
    }
}
