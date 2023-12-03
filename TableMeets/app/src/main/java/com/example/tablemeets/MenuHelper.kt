package com.example.tablemeets

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu

class MenuHelper(private val context: Context) : IMenuClickListener {
    fun showPopupMenu(view: View, menuClickListener: IMenuClickListener) {
        val popupMenu = PopupMenu(context, view)
        popupMenu.menuInflater.inflate(R.menu.menu_main, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.create_event -> {
                    menuClickListener.goToCreateEvent()
                    true
                }
                R.id.events_created -> {
                    menuClickListener.goToEventsCreated()
                    true
                }
                R.id.attending_events -> {
                    menuClickListener.goToAttendingEvents()
                    true
                }
                R.id.search_event -> {
                    menuClickListener.goToSearchEvent()
                    true
                }
                R.id.games -> {
                    menuClickListener.goToGames()
                    true
                }
                R.id.profile -> {
                    menuClickListener.goToProfile()
                    true
                }
                R.id.settings -> {
                    menuClickListener.goToSettings()
                    true
                }
                R.id.logout -> {
                    menuClickListener.logout()
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }


    override fun goToHome() {
        val intent = Intent(context, Home::class.java)
        context.startActivity(intent)
    }
    override fun goToCreateEvent() {
        val intent = Intent(context, NewEvent::class.java)
        context.startActivity(intent)
    }

    override fun goToTypeOfEvent() {
        val intent = Intent(context, TypeOfEvent::class.java)
        context.startActivity(intent)
    }
    override fun goToSearchEvent() {
        val intent = Intent(context, SearchEvent::class.java)
        context.startActivity(intent)
    }

    override fun goToGames() {
            val intent = Intent(context, Games::class.java)
        context.startActivity(intent)
    }

    override fun goToEventsCreated(){

        val intent = Intent(context, EventsCreated::class.java)
        context.startActivity(intent)
    }

    override fun goToAttendingEvents (){

        val intent = Intent(context, AttendingEvents::class.java)
        context.startActivity(intent)
    }

    override fun goToSettings (){

        val intent = Intent(context, Settings::class.java)
        context.startActivity(intent)
    }
    override fun goToProfile (){

        val intent = Intent(context, Profile::class.java)
        context.startActivity(intent)
    }

    override fun goToLogin (){

        val intent = Intent(context, Login::class.java)
        context.startActivity(intent)
    }

    override fun logout (){
        goToLogin()
    }



}