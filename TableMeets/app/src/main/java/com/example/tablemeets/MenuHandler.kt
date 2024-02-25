package com.example.tablemeets

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.view.LayoutInflater
import android.widget.Toast

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MenuHandler(private val context: Context) : NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar

    fun showMenu() {
        val inflater = LayoutInflater.from(context)
        val menuView = inflater.inflate(R.layout.menu_layout, null)

        drawerLayout = menuView.findViewById(R.id.drawer_layout)
        navigationView = menuView.findViewById(R.id.nav_view)
        toolbar = menuView.findViewById(R.id.toolbar)

        // Configura el toolbar como la ActionBar de la actividad
        (context as AppCompatActivity).setSupportActionBar(toolbar)

        // Configura el ActionBarDrawerToggle con el Toolbar
        val toggle = ActionBarDrawerToggle(
            context, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Abre el cajón de navegación
        drawerLayout.openDrawer(GravityCompat.START)

        // Asegura que el NavigationView esté delante de otras vistas
        navigationView.bringToFront()

        navigationView.setNavigationItemSelectedListener(this)
    }

    

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (menuItem.itemId) {
            R.id.create_event -> {
                // Handle click on create event menu item
                true
            }
            R.id.events_created -> {
                // Handle click on events created menu item
                true
            }
            R.id.attending_events -> {
                // Handle click on attending events menu item
                true
            }
            R.id.search_event -> {
                // Handle click on search event menu item
                true
            }
            R.id.games -> {
                // Handle click on games menu item
                true
            }
            R.id.profile -> {
                // Handle click on profile menu item
                true
            }
            R.id.settings -> {
                // Handle click on settings menu item
                true
            }
            R.id.logout -> {
                // Handle click on logout menu item
                true
            }
            else -> false
        }

        // Cerrar el DrawerLayout después de hacer clic en un elemento del menú
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


    // this method is not necessary if we use (setNavigationItemSelectedListener)
    // method to display options ---> but there are some complications...
    fun showPopupMenu(view: View, navigationHelper: NavigationHelper) {
        val popupMenu = PopupMenu(context, view)
        val authenticationHelper = AuthenticationHelper(context)
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
                    authenticationHelper.logout(context)
                    navigationHelper.goToLogin()
                    true
                }

                else -> false
            }
        }
        popupMenu.show()
    }
}
