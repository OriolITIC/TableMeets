package com.example.tablemeets

import android.content.Context
import android.content.Intent
import androidx.appcompat.widget.Toolbar

class NavigationHelper(private val context: Context) : INavigationHelper{

   /* override fun setToolBar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_home)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }*/

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
    override fun goToEditProfile (){
        val intent = Intent(context, EditProfile::class.java)
        context.startActivity(intent)
    }
    override fun goToRegister (){
        val intent = Intent(context, Register::class.java)
        context.startActivity(intent)
    }

    override fun goToForgotPassword (){
        val intent = Intent(context, ForgotPassword::class.java)
        context.startActivity(intent)
    }

    override fun goToAboutUs() {
        val intent = Intent(context, AboutUs::class.java)
        context.startActivity(intent)
    }

    override fun goToDescriptionCreatedEvent() {
        val intent = Intent(context, DescriptionCreatedEvent::class.java)
        context.startActivity(intent)
    }

    override fun goToDescriptionAttendingEvent() {
        val intent = Intent(context, DescriptionAttendingEvent::class.java)
        context.startActivity(intent)
    }

    override fun logout (){
        goToLogin()
    }

}