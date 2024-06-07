package com.example.notesjetpack.ui.viewmodel


import androidx.lifecycle.ViewModel
import com.example.notesjetpack.data.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NotesViewModel:ViewModel() {
    private val _uiState = MutableStateFlow(NotesUiState())
    val uiState:StateFlow<NotesUiState> = _uiState.asStateFlow()


    private var nextId = 1

    fun addNote(){
        if(_uiState.value.title.isBlank()){
            return
        }
        val newNote = Note(
            id = nextId++,
            title = _uiState.value.title,
            body = _uiState.value.body
        )
        _uiState.update { currentState->
            currentState.copy(
                notes = currentState.notes + newNote,
                title = "",
                body = ""
            )
        }

    }
    fun updateNote(id:Int,title:String,body:String){
        if(title.isBlank()){
            return
        }
        _uiState.update {currentState->
            val updatedNotes = currentState.notes.map { note->
                if(note.id==id){
                    note.copy(title = title, body = body)
                }
                else{
                    note
                }
            }
            currentState.copy(notes = updatedNotes,title="",body="")
        }
    }
    fun deleteNote(id:Int){
        _uiState.update {currentState->
            currentState.copy(
                notes = currentState.notes.filter { note->
                    note.id != id
                },
                title = "",
                body = ""

            )

        }
    }
    fun updateTitle(title: String) {
        _uiState.update { currentState ->
            currentState.copy(title = title)
        }
    }

    fun updateBody(body: String) {
        _uiState.update { currentState ->
            currentState.copy(body = body)
        }
    }


}