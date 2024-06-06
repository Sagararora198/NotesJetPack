package com.example.notesjetpack.ui.navigation

sealed class Screen(val route:String) {
    data object NotesList:Screen("notes_list")
    data object AddNote:Screen("add_note")
    data object NoteDetail:Screen("note_detail")

    fun withArgs(vararg args:Int):String{
        return buildString {
            append(route)
            args.forEach { arg->
                append("/$arg")
            }
        }
    }


}