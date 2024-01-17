package com.example.tablemeets

import android.content.Context
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.core.view.GravityCompat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

//class MenuHandler(private val context: Context) {


class MenuHandler(private val context: Context) : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_layout)
        setToolBar()

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)

    }

    public fun setToolBar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_home)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    //Then Generate --> Override Methods : ----> search for (onOptions) method :

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    fun showPopupMenu(view: View, navigationHelper: NavigationHelper) {
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