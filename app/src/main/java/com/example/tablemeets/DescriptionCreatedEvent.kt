package com.example.tablemeets

import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DescriptionCreatedEvent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.description_created_event)

        val menuIcon = findViewById<ImageView>(R.id.menu_icon)
        val menuHelper = MenuHandler(this)
        val navigationHelper = NavigationHelper(this)
        val eventName = intent.getStringExtra("event_name")
        val gameName = intent.getStringExtra("event_game")
        val location = intent.getStringExtra("event_location")
        val time = intent.getStringExtra("event_time")
        val date = intent.getStringExtra("event_date")
        val description = intent.getStringExtra("event_description")
        val eventNameTextView = findViewById<TextView>(R.id.event_title)

        eventName?.let {
            eventNameTextView.text = it
        }


        val scrollView = findViewById<ScrollView>(R.id.textViewScrollable)
        val textViewScrollable = scrollView.findViewById<TextView>(R.id.textViewInfo)

        val eventInfo = getString(R.string.event_info, gameName, location, date, time, description)
        val styledEventInfo = Html.fromHtml(eventInfo)

        // Establecer la información del evento en el TextView
        textViewScrollable.text = styledEventInfo

        menuIcon.setOnClickListener {
            menuHelper.showPopupMenu(it,navigationHelper)

        }
    }
}