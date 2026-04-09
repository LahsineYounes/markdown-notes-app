package com.example.markdownnotesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.markdownnotesapp.model.Note
import com.example.markdownnotesapp.ui.EditorScreen
import com.example.markdownnotesapp.ui.HomeScreen

// App entry point + navigation between screens
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                // selectedNote holds the note being edited (null = new note)
                var selectedNote by remember { mutableStateOf<Note?>(null) }

                NavHost(navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen(
                            onNoteClick = { note ->
                                selectedNote = note
                                navController.navigate("editor")
                            },
                            onNewNote = {
                                selectedNote = null
                                navController.navigate("editor")
                            }
                        )
                    }
                    composable("editor") {
                        EditorScreen(
                            existingNote = selectedNote,
                            onBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}