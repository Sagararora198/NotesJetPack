package com.example.notesjetpack.ui.screens

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.notesjetpack.ui.viewmodel.NotesViewModel

@Composable
fun AddNote(
    viewModel: NotesViewModel,
    onCancel:()->Unit,
    onSaveNote: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    BackHandler {
        if (uiState.title.isNotBlank()) {
            onSaveNote()
        } else {
            onCancel()
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 32.dp), // Add padding to the top
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Add Note",
                    style = MaterialTheme.typography.bodyMedium
                )
                TextField(
                    value = uiState.title,
                    onValueChange = { viewModel.updateTitle(it) },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = uiState.body,
                    onValueChange = { viewModel.updateBody(it) },
                    label = { Text("Body") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
                Button(

                    onClick = {
                              if(uiState.title.isBlank()){
                                  Toast.makeText(context,"Title cannot be empty",Toast.LENGTH_LONG).show()
                              }
                        else{
                            onSaveNote()
                              }
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Save Note")
                }
            }
        }
    }
}