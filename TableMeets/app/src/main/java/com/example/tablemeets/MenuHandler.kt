package com.example.tablemeets

import android.content.Context
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu

class MenuHandler(private val context: Context){
    fun showPopupMenu(view: View, navigationHelper: INavigationHelper) {
        val popupMenu = PopupMenu(context, view)
        popupMenu.menuInflater.inflate(R.menu.menu_main, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.create_event -> {
                    navigationHelper.goToCreateEvent()
                    true
                }
                R.id.events_created -> {
                    navigationHelper.goToEventsCreated()
                    true
                }
                R.id.attending_events -> {
                    navigationHelper.goToAttendingEvents()
                    true
                }
                R.id.search_event -> {
                    navigationHelper.goToSearchEvent()
                    true
                }
                R.id.games -> {
                    navigationHelper.goToGames()
                    true
                }
                R.id.profile -> {
                    navigationHelper.goToProfile()
                    true
                }
                R.id.settings -> {
                    navigationHelper.goToSettings()
                    true
                }
                R.id.logout -> {
                    navigationHelper.logout()
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }






}