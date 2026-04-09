package com.example.markdownnotesapp.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.markdownnotesapp.NoteViewModel
import com.example.markdownnotesapp.model.Note
import com.example.markdownnotesapp.ui.theme.CardWhite
import com.example.markdownnotesapp.ui.theme.Cream
import com.example.markdownnotesapp.ui.theme.DeepPurple
import com.example.markdownnotesapp.ui.theme.TagBlue
import com.example.markdownnotesapp.ui.theme.TagBlueDark
import com.example.markdownnotesapp.ui.theme.WarmGray

// list of notes
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: NoteViewModel = viewModel(),
    onNoteClick: (Note) -> Unit,
    onNewNote: () -> Unit
) {
    val notes by viewModel.notes.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Scaffold(
        containerColor = Cream,
        topBar = {
            Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)) {
                Text(
                    "My Notes",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = DeepPurple
                )
                Spacer(Modifier.height(8.dp))
                // Search bar
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { viewModel.setSearch(it) },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Search notes...", color = WarmGray) },
                    leadingIcon = { Icon(Icons.Filled.Search, null, tint = WarmGray) },
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = CardWhite,
                        unfocusedContainerColor = CardWhite,
                        focusedBorderColor = DeepPurple,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    singleLine = true
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNewNote,
                containerColor = DeepPurple,
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(Icons.Filled.Add, "New note", tint = Color.White)
            }
        }
    ) { padding ->
        if (notes.isEmpty()) {
            // Empty state
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("✏️", fontSize = 48.sp)
                    Spacer(Modifier.height(8.dp))
                    Text("No notes yet", color = WarmGray, fontSize = 16.sp)
                    Text("Tap + to create one", color = WarmGray, fontSize = 13.sp)
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.padding(padding),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(notes, key = { it.id }) { note ->
                    NoteCard(
                        note = note,
                        onClick = { onNoteClick(note) },
                        onDelete = { viewModel.delete(note) }
                    )
                }
            }
        }
    }
}

@Composable
fun NoteCard(note: Note, onClick: () -> Unit, onDelete: () -> Unit) {
    var showMenu by remember { mutableStateOf(false) }

    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        border = BorderStroke(1.dp, Color(0xFFEEEEEE))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    note.title.ifBlank { "Untitled" },
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )
                Box {
                    IconButton(onClick = { showMenu = true }) {
                        Icon(Icons.Filled.MoreVert, null, tint = WarmGray)
                    }
                    DropdownMenu(showMenu, onDismissRequest = { showMenu = false }) {
                        DropdownMenuItem(
                            text = { Text("Delete", color = Color.Red) },
                            onClick = { onDelete(); showMenu = false }
                        )
                    }
                }
            }
            // Tag chip
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = TagBlue,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Text(
                    note.tag,
                    fontSize = 11.sp,
                    color = TagBlueDark,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp)
                )
            }
            Spacer(Modifier.height(4.dp))
            Text(
                // Show a preview — first 100 chars of content
                note.content.take(100).let { if (note.content.length > 100) "$it…" else it },
                color = WarmGray,
                fontSize = 13.sp,
                lineHeight = 18.sp,
                maxLines = 3
            )
        }
    }
}