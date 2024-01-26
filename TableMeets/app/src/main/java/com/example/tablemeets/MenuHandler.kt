/*package com.example.tablemeets

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

    fun setToolBar() {
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

}*/




import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.tablemeets.NavigationHelper
import com.example.tablemeets.R
import com.google.android.material.navigation.NavigationView

class MenuHandler(private val context: Context) : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_layout)

        // Initialize views
        toolbar = findViewById(R.id.toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)

        // Set up toolbar
        setToolBar()

        // Set up the drawer toggle
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Set up the item click listener for the NavigationView
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle item click here if needed
            when (menuItem.itemId) {
                // Handle navigation items if needed
            }
            true
        }
    }

    private fun setToolBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_home)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
                return true
            }
            // Handle other menu items if needed
            else -> return super.onOptionsItemSelected(item)
        }
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


/*
class MenuHandler(private val context: Context) : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_layout)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this,
            drawerLayout,toolbar,R.string.what_we_offer,R.string.our_mission_description_part1)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container,()).commit()
            navigationView.setCheckedItem(R.id.nav_home)
        }

    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container,HomeFragment()).commit()

            R.id.nav_setting -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container,SettingFragment()).commit()

            R.id.nav_share -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container,ShareFragment()).commit()

            R.id.nav_about -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container,AboutFragment()).commit()

            R.id.nav_logout -> Toast.makeText(this,
                "Logout",Toast.LENGTH_SHORT).show()
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        else{
            onBackPressedDispatcher.onBackPressed()
        }
    }

}
 */