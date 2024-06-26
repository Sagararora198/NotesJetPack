package com.example.notesjetpack.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notesjetpack.ui.screens.AddNote
import com.example.notesjetpack.ui.screens.NoteDetail
import com.example.notesjetpack.ui.screens.NotesList
import com.example.notesjetpack.ui.viewmodel.NotesViewModel

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController(),
    viewModel: NotesViewModel = viewModel()
) {

    NavHost(navController = navController, startDestination = Screen.NotesList.route) {
        composable(route = Screen.NotesList.route) {
            NotesList(
                viewModel = viewModel,
                onAddNote = { navController.navigate(Screen.AddNote.route) }) { noteId ->
                navController.navigate(Screen.NoteDetail.withArgs(noteId))
            }
        }
        composable(route = Screen.AddNote.route) {
            AddNote(viewModel = viewModel, onCancel = { navController.popBackStack() }) {
                viewModel.addNote()
                navController.popBackStack()

            }
        }
        composable(route = Screen.NoteDetail.route + "/{noteId}",
            arguments = listOf(
                navArgument("noteId") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { backstackEntry ->
            val noteId = backstackEntry.arguments?.getString("noteId")?.toInt()
            NoteDetail(noteId = noteId!!, viewModel = viewModel, onEditNote = { title, body ->
                viewModel.updateNote(noteId, title, body)
                navController.popBackStack()
            }) {
                viewModel.deleteNote(noteId)
                navController.popBackStack()

            }


        }
    }
}