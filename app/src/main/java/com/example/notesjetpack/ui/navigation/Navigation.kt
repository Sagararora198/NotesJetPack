package com.example.notesjetpack.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notesjetpack.ui.AddNote
import com.example.notesjetpack.ui.NoteDetail
import com.example.notesjetpack.ui.NotesList
import com.example.notesjetpack.ui.NotesViewModel

@Composable
fun Navigation(navController:NavHostController = rememberNavController(),viewModel : NotesViewModel){

    NavHost(navController = navController, startDestination = Screen.NotesList.route){
        composable(route=Screen.NotesList.route){
            NotesList(viewModel =viewModel , onAddNote = { navController.navigate(Screen.AddNote.route) }) {noteId->
                navController.navigate(Screen.NoteDetail.withArgs(noteId))
            }
        }
        composable(route=Screen.AddNote.route){
            AddNote(viewModel = viewModel, onCancel = { navController.popBackStack() }) {
                viewModel.addNote()
                navController.popBackStack()
                
            }
        }
        composable(route=Screen.NoteDetail.route + "/{noteId}",
            arguments = listOf(
                navArgument("noteId"){
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ){backstackEntry->
            val noteId = backstackEntry.arguments?.getString("noteId")?.toInt()
            NoteDetail(noteId = noteId!!, viewModel = viewModel ,onEditNote ={
                title,body ->
                viewModel.updateNote(noteId,title,body)
                navController.popBackStack()
            } ) {
                viewModel.deleteNote(noteId)
                navController.popBackStack()

            }


        }
    }
}