package com.example.notesjetpack

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.notesjetpack.ui.navigation.Navigation
import com.example.notesjetpack.ui.navigation.Screen
import com.example.notesjetpack.ui.viewmodel.NotesViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import junit.framework.TestCase.assertEquals

class NotesScreenNavigationTest {


    private lateinit var navController: TestNavHostController
    private lateinit var viewModel:NotesViewModel

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setupNotesNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
        //ensure that there are some element in the notes in order to test the note detail page
            viewModel = NotesViewModel().apply {
                updateTitle("Sample title")
                updateBody("Sample body")
                addNote()
            }
            Navigation(navController = navController,viewModel)

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

    @Test
    fun noteNavHost_verifyOnItemClick_navigateToNoteDetailScreen(){
        navigateToNoteDetailScreen()
        navController.assertCurrentRouteName(Screen.NoteDetail.route + "/{noteId}")

    }



    private fun navigateToNoteDetailScreen(){
        composeTestRule.onNodeWithText("Sample title").performClick()
    }
    private fun navigateToAddNoteScreen(){
        composeTestRule.onNodeWithStringId(R.string.btn_addnote).performClick()
    }
}