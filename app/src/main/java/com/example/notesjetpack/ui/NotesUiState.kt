package com.example.notesjetpack.ui

import com.example.notesjetpack.data.Note

data class NotesUiState(
    val notes:List<Note> = emptyList(),
    val title:String = "" ,
    val body:String = ""

)
