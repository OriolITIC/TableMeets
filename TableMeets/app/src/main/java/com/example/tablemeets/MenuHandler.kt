package com.example.tablemeets

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MenuHandler(private val context: Context) : AppCompatActivity() {

    // needed if setNavigationItemSelectedListener method is used :
    //private var navigationHelper: NavigationHelper

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_layout)

        setToolBar()

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        toolbar = findViewById(R.id.toolbar)


        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.create_event -> {
                    //navigationHelper.goToCreateEvent()
                    true
                }

                R.id.events_created -> {
                    //navigationHelper.goToEventsCreated()
                    true
                }

                R.id.attending_events -> {
                    //navigationHelper.goToAttendingEvents()
                    true
                }

                R.id.search_event -> {
                    //navigationHelper.goToSearchEvent()
                    true
                }

                R.id.games -> {
                    //navigationHelper.goToGames()
                    true
                }

                R.id.profile -> {
                    //navigationHelper.goToProfile()
                    true
                }

                R.id.settings -> {
                    //navigationHelper.goToSettings()
                    true
                }

                R.id.logout -> {
                    //navigationHelper.logout()
                    true
                }

                else -> false
            }
        }

    }

    private fun setToolBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_home)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    // this method is not necessary if we use (setNavigationItemSelectedListener)
    // method to display options ---> but there are some complications...
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
