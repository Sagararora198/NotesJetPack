package com.example.notesjetpack.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.notesjetpack.ui.viewmodel.NotesViewModel

@Composable
fun NotesList(
    viewModel: NotesViewModel,
    onAddNote: () -> Unit,
    onNoteClick: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)) // Set a light background color
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .border(2.dp, Color.Gray)
                .background(Color.LightGray)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Notes App",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        }
        Box(modifier = Modifier.fillMaxSize()) {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .padding(top = 16.dp) // Add padding to the top
            ) {
                items(uiState.notes) { note ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable { onNoteClick(note.id) },
                        elevation = CardDefaults.cardElevation(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = note.title,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold
                            )
                            Divider(modifier = Modifier.padding(vertical = 8.dp))
                            Text(
                                text = note.body,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
            FloatingActionButton(
                onClick = onAddNote,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(64.dp)
            ) {
                Text("+")
            }
        }
    }
}