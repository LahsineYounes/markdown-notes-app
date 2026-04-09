package com.example.markdownnotesapp.data

// Room setup : Creates the database file on the device
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.example.markdownnotesapp.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context, NoteDatabase::class.java, "notes_db")
                    .build().also { INSTANCE = it }
            }
        }
    }
}