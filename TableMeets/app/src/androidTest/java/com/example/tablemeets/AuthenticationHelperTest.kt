package com.example.tablemeets

import android.content.Context
import org.junit.Assert.*
import com.example.tablemeets.AuthenticationHelper
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class AuthenticationHelperTest {

    @Mock
    private lateinit var mockContext: Context

    private lateinit var authenticationHelper: AuthenticationHelper

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        authenticationHelper = AuthenticationHelper(mockContext)
    }

    @Test
    fun testRegisterUser() {
        `when`(mockContext.getSharedPreferences("MyPrefs",
            Context.MODE_PRIVATE)).thenReturn(null)

        val result = authenticationHelper.registerUser(mockContext,
            "testuser", "test@example.com",
            "Test123456!", "Test123456!")

        assertEquals(true, result)
    }

    @Test
    fun testLoginUser_Validate() {
        `when`(mockContext.getSharedPreferences("MyPrefs",
            Context.MODE_PRIVATE)).thenReturn(null)

        val result = authenticationHelper.loginUser(mockContext,
            "testuser", "Test123456@")

        assertEquals(true, result)
    }

    @Test
    fun testIsUserAuthenticated() {
        val result = authenticationHelper.isUserAuthenticated()
        assertEquals(false, result)
    }


}