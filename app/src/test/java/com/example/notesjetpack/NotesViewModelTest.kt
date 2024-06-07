package com.example.notesjetpack

import com.example.notesjetpack.ui.viewmodel.NotesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import junit.framework.TestCase.assertEquals


class NotesViewModelTest {
    private lateinit var viewModel:NotesViewModel

    @Before
    fun setup(){
            viewModel = NotesViewModel()
    }


    @Test
    fun testUpdateTitle() {
        viewModel.updateTitle("New Title")

        val uiState = viewModel.uiState.value
        assertEquals("New Title", uiState.title)
    }

    @Test
    fun testUpdateBody() {
        viewModel.updateBody("New Body")

        val uiState = viewModel.uiState.value
        assertEquals("New Body", uiState.body)
    }

    @Test
    fun testAddNote_success_noteAddedToList() {
        viewModel.updateTitle("New Title")
        viewModel.updateBody("New Body")
        viewModel.addNote()

        val uiState = viewModel.uiState.value
        assertEquals(1,uiState.notes.size)
        assertEquals("New Title",uiState.notes[0].title)
        assertEquals("New Body",uiState.notes[0].body)


    }
    @Test
    fun testAddNoteWithoutTitle_failure_noteNotAdded(){
        viewModel.updateTitle("")
        viewModel.updateBody("New Body")
        viewModel.addNote()
        val uiState = viewModel.uiState.value
        assertEquals(0,uiState.notes.size)
    }
    @Test
    fun testUpdateNote() {
        viewModel.updateTitle("Test Title")
        viewModel.updateBody("Test Body")
        viewModel.addNote()

        viewModel.updateNote(
            id = 1,
            title = "Updated Title",
            body = "Updated Body"
        )

        val uiState = viewModel.uiState.value
        assertEquals(1, uiState.notes.size)
        assertEquals("Updated Title", uiState.notes[0].title)
        assertEquals("Updated Body", uiState.notes[0].body)
    }

    @Test
    fun testDeleteNote() {
        viewModel.updateTitle("Test Title")
        viewModel.updateBody("Test Body")
        viewModel.addNote()

        viewModel.deleteNote(
            id = 1
        )

        val uiState = viewModel.uiState.value
        assertEquals(0, uiState.notes.size)
    }



}