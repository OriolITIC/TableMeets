package com.example.tablemeets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tablemeets.controller.GameAdapter

class Games : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.games)

        val menuHelper = MenuHandler(this)
        val navigationHelper = NavigationHelper(this)
        val menuIcon = findViewById<ImageView>(R.id.menu_icon)
        val homeLogo = findViewById<ImageView>(R.id.home_logo)

        menuIcon.setOnClickListener {
            menuHelper.showPopupMenu(it, navigationHelper)
        }

        homeLogo.setOnClickListener {
            navigationHelper.goToHome()
        }
        val gamesList = listOf(
            "Ajedrez",
            "Damas",
            "Ludo",
            "Dixit",
            "Go",
            "Shogi",
            "Catan",
            "Pandemic",
            "Dominion",
            "Taboo",
            "Monopoly",
            "Risk",
            "Carcassone",
            "Jenga",
            "Ticket to Ride",

        )

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewGames) // Ajustado al ID correcto
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = GameAdapter(gamesList, object : GameAdapter.OnGameClickListener {
            override fun onGameClick(position: Int) {
                // Manejar el clic del juego aquí si es necesario
            }
        })
        recyclerView.adapter = adapter
    }
}