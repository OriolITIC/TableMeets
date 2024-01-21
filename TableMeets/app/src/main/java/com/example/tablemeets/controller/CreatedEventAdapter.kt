package com.example.tablemeets.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tablemeets.R
import com.example.tablemeets.data.CreatedEvent

class CreatedEventAdapter(private val eventList: List<CreatedEvent>) :
    RecyclerView.Adapter<CreatedEventAdapter.CreatedEventViewHolder>() {

    class CreatedEventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textViewEvent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatedEventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event_created, parent, false)
        return CreatedEventViewHolder(view)
    }

    override fun onBindViewHolder(holder: CreatedEventViewHolder, position: Int) {
        val event = eventList[position]

        val formattedText = "Nombre: ${event.gameName}\nUbicación: ${event.location}\nFecha: ${event.date}\nHora: ${event.time}"

        holder.textView.text = formattedText
    }


    override fun getItemCount(): Int {
        return eventList.size
    }
}

