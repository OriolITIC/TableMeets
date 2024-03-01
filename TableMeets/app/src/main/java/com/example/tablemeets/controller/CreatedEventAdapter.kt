package com.example.tablemeets.controller

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tablemeets.DescriptionCreatedEvent
import com.example.tablemeets.R
import com.example.tablemeets.data.Event

class CreatedEventAdapter(private val eventList: List<Event>) :
    RecyclerView.Adapter<CreatedEventAdapter.EventViewHolder>() {

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textViewEvent)
        val aboutButton: ImageView = itemView.findViewById(R.id.about_event)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event_created, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = eventList[position]

        val formattedText = "${position + 1}. ${event.eventName}"

        holder.textView.text = formattedText

        holder.aboutButton.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DescriptionCreatedEvent::class.java)
            intent.putExtra("event_name", event.eventName)
            intent.putExtra("event_game", event.gameName)
            intent.putExtra("event_location", event.location)
            intent.putExtra("event_date", event.eventDate)
            intent.putExtra("event_time", event.eventTime)
            intent.putExtra("event_description", event.description)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return eventList.size
    }
}







