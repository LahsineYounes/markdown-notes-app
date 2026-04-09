package com.example.markdownnotesapp.model

// A data class (what a note looks like)
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val content: String,
    val tag: String = "General",         // e.g. "Work", "Personal"
    val timestamp: Long = System.currentTimeMillis()
)