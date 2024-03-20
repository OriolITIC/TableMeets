package com.example.tablemeets.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tablemeets.R

class GameAdapter(private val games: List<String>, private val onGameClickListener: OnGameClickListener) :
    RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    interface OnGameClickListener {
        fun onGameClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = games[position]
        holder.bind(game)
        holder.buttonAboutGame.setOnClickListener {
            onGameClickListener.onGameClick(position)
        }
    }

    override fun getItemCount(): Int {
        return games.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewGame: TextView = itemView.findViewById(R.id.textViewGame)
        val buttonAboutGame: ImageView = itemView.findViewById(R.id.about_game)

        fun bind(game: String) {
            textViewGame.text = game
        }
    }
}

