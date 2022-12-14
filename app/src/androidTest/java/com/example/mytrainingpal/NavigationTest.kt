package com.example.mytrainingpal

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.mytrainingpal.composables.AppNavHost
import com.example.mytrainingpal.composables.Screen
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

// Tests inspired by https://developer.android.com/codelabs/jetpack-compose-navigation#9

class NavigationTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            AppNavHost(navController = navController)
        }
    }

    @Test
    fun appNavHost_verifyStartDestination() {
        composeTestRule.runOnIdle {
            assertEquals(navController.currentDestination?.route, Screen.Home.route)
        }
    }

    @Test
    fun bottomNavBar_verifyStartTabHomeSelected() {
        composeTestRule.onNodeWithText("Home").assertIsSelected()
    }

    @Test
    fun bottomNavBar_verifyStartTabOnlyHomeSelected() {
        composeTestRule.onNodeWithText("Calendar").assertIsNotSelected()
        composeTestRule.onNodeWithText("Muscle Pain").assertIsNotSelected()
        composeTestRule.onNodeWithText("Training").assertIsNotSelected()
    }

    @Test
    fun bottomNavBar_verifyNavToCalendar() {
        composeTestRule.onNodeWithText("Calendar").performClick()
        composeTestRule.runOnIdle {
            assertEquals(navController.currentDestination?.route, Screen.CalendarMain.route)
        }
    }

    @Test
    fun homeFloatingButton_verifyNavToTraining() {
        composeTestRule.onNodeWithContentDescription("Start Training").performClick()
        composeTestRule.runOnIdle {
            assertEquals(navController.currentDestination?.route, Screen.TrainingMain.route)
        }
    }
}

