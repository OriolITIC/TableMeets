package com.example.tablemeets

import android.content.Context
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu

object MenuHelper {
    fun showPopupMenu(
        context: Context,
        view: View,
        menuClickListener: MenuClickListener
    ) {
        val popupMenu = PopupMenu(context, view)
        popupMenu.menuInflater.inflate(R.menu.menu_main, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.create_event -> {
                    menuClickListener.goToCreateEvent()
                    true
                }
                R.id.events_created_by_me -> {

                    true
                }


                else -> false
            }
        }

        popupMenu.show()
    }
}