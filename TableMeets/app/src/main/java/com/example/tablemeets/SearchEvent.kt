package com.example.tablemeets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tablemeets.controller.GameAdapter

class SearchEvent: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_event)

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
        val eventsList = listOf(
            "Evento1",
            "Evento2",
            "Evento3",
            "Evento4",
            "Evento5",
            "Evento6",
            "Evento7",
            "Evento8",
            "Evento9",
            "Evento10",
            "Evento11",
            "Evento12",
            "Evento13",
            "Evento14",
            "Evento15",
            )

        val recyclerView: RecyclerView =
            findViewById(R.id.recyclerViewEvents)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = GameAdapter(eventsList, object : GameAdapter.OnGameClickListener {
            override fun onGameClick(position: Int) {
                // Manejar el clic del juego aquí si es necesario
            }
        })
        recyclerView.adapter = adapter
    }
}
