package com.example.notesjetpack.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.notesjetpack.ui.viewmodel.NotesViewModel

@Composable
fun NoteDetail(
    noteId: Int,
    viewModel: NotesViewModel,
    onEditNote: (String, String) -> Unit,
    onDeleteNote: () -> Unit
) {
    var visibility by rememberSaveable {
        mutableStateOf(false
        )
    }
    val uiState by viewModel.uiState.collectAsState()
    val note = uiState.notes.find { it.id == noteId } ?: return

    var title by rememberSaveable { mutableStateOf(note.title) }
    var body by rememberSaveable   { mutableStateOf(note.body) }

    val context = LocalContext.current
    


    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Edit Note",
                style = MaterialTheme.typography.bodyMedium
            )
            TextField(
                value = title,
                onValueChange = { title = it
                                visibility = true},
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = body,
                onValueChange = { body = it
                                visibility = true},
                label = { Text("Body") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    enabled = visibility,
                    onClick = { if(title.isBlank()){
                        Toast.makeText(context,"Title cannot be empty",Toast.LENGTH_LONG).show()
                        }
                    else{
                        onEditNote(title, body)
                        }
                        },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Edit Note")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = onDeleteNote,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Delete Note")
                }
            }
        }
    }
}

