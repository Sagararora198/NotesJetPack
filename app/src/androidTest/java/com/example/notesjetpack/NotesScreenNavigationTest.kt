package com.example.notesjetpack

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.notesjetpack.ui.navigation.Navigation
import com.example.notesjetpack.ui.navigation.Screen
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import junit.framework.TestCase.assertEquals

class NotesScreenNavigationTest {


    private lateinit var navController: TestNavHostController

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setupNotesNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            Navigation(navController = navController)
        }
    }

    @Test
    fun notesNavHost_verifyStartDestination(){
        navController.assertCurrentRouteName(Screen.NotesList.route)
    }


    @Test
    fun notesNavHost_verifyOnAddNoteClick_navigateToAddNoteScreen(){
        navigateToAddNoteScreen()
        navController.assertCurrentRouteName(Screen.AddNote.route)
    }


    private fun navigateToAddNoteScreen(){
        composeTestRule.onNodeWithStringId(R.string.btn_addnote).performClick()
    }
}