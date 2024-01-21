package com.example.tablemeets.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tablemeets.R

class AttendingEventAdapter(private val joinedEventList: List<String>) : RecyclerView.Adapter<AttendingEventAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textViewEvent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_attending_event, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val joinedEventItem = joinedEventList[position]
        holder.textView.text = joinedEventItem
    }

    override fun getItemCount(): Int {
        return joinedEventList.size
    }
}
