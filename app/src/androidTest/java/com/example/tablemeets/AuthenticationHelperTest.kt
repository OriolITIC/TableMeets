package com.example.tablemeets

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tablemeets.AuthenticationHelper
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AuthenticationHelperTest {

    private lateinit var context: Context
    private lateinit var authenticationHelper: AuthenticationHelper

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        authenticationHelper = AuthenticationHelper(context)
    }

    @Test
    fun testRegisterUser() {
        assertTrue(authenticationHelper.registerUser(context,
            "testUser",
            "test@example.com",
            "aA123456*",
            "aA123456*"))
    }

    @Test
    fun testLoginUser() {
        assertTrue(authenticationHelper.registerUser(context,
            "testUser", "test@example.com",
            "aA123456*", "aA123456*"))
        assertTrue(authenticationHelper.loginUser(context,
            "testUser", "aA123456*"))
    }


    @Test
    fun testCheckUserExistence() {
        assertFalse(authenticationHelper.checkUserExistence("nonExistingUser"))
        authenticationHelper.registerUser(context,
            "existingUser", "existing@example.com",
            "bB123456*", "bB123456*")
        assertTrue(authenticationHelper.checkUserExistence("existingUser"))
    }

    @Test
    fun testVerifyPassword() {
        // Valid
        assertTrue(authenticationHelper.verifyPassword(context,
            "pP123456@"))
        // Invalid:
        assertFalse(authenticationHelper.verifyPassword(context,
            "password"))
        // Invalid:
        assertFalse(authenticationHelper.verifyPassword(context,
            "Password"))
    }

    @Test
    fun testIsValidEmail() {
        // Valid
        assertTrue(authenticationHelper.isValidEmail("test@example.com"))
        // Invalid:
        assertFalse(authenticationHelper.isValidEmail("test@example"))
    }

    /*@Test
    fun testIsUserAuthenticated() {
        assertFalse(authenticationHelper.isUserAuthenticated())
        authenticationHelper.registerUser(context,
            "testUser", "test@example.com",
            "aA123456*", "aA123456*")
        assertTrue(authenticationHelper.isUserAuthenticated())
    }

    @Test
    fun testGetAuthenticatedUsername() {
        // No user authenticated initially
        assertEquals("", authenticationHelper.getAuthenticatedUsername())
        authenticationHelper.registerUser(context, "testUser", "test@example.com", "password", "password")
        assertEquals("testUser", authenticationHelper.getAuthenticatedUsername())
    }

    @Test
    fun testLogout() {
        authenticationHelper.registerUser(context,
            "testUser",
            "test@example.com",
            "aA123456*",
            "aA123456*")
        authenticationHelper.logout(context)
        // After logout, no user should be authenticated
        assertEquals("", authenticationHelper.getAuthenticatedUsername())
    }*/
}
