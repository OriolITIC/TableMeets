package com.example.tablemeets

import android.view.MenuItem

// MenuClickListener.kt
interface INavigationHelper {

    /*fun setToolBar()

    fun onOptionsItemSelected(item: MenuItem)*/

    fun goToHome()
    fun goToCreateEvent()
    fun goToTypeOfEvent()
    fun goToSearchEvent()
    fun goToGames()
    fun goToEventsCreated()
    fun goToAttendingEvents()
    fun goToSettings()
    fun goToProfile()
    fun goToEditProfile()
    fun goToRegister()
    fun goToForgotPassword()
    fun goToLogin()
    fun logout()
    fun goToAboutUs()
    fun goToDescriptionCreatedEvent()
    fun goToDescriptionAttendingEvent()



}
