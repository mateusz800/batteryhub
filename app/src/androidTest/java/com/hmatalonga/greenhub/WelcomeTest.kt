package com.hmatalonga.greenhub

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hmatalonga.greenhub.ui.welcome.WelcomeActivity

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.content.Intent

import com.hmatalonga.greenhub.ui.MainActivity
import org.junit.Assert.assertEquals


@RunWith(AndroidJUnit4::class)
class WelcomeTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule(WelcomeActivity::class.java)

    @Before
    fun setup(){
        composeTestRule.waitForIdle()
    }

    @Test
    fun exitAppOnTosDecline() {
        /*
        composeTestRule.onNodeWithText("Accept").performClick()
        val expectedIntent = Intent(composeTestRule.activity, MainActivity::class.java)
        val actualIntent = composeTestRule.activity.intent
        assertEquals(expectedIntent, actualIntent)
         */


    }
}