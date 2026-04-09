package com.example.markdownnotesapp.data

// SQL queries (insert, delete, get all)
import androidx.room.*
import com.example.markdownnotesapp.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes ORDER BY timestamp DESC")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM notes WHERE title LIKE '%' || :query || '%' OR content LIKE '%' || :query || '%'")
    fun searchNotes(query: String): Flow<List<Note>>

    @Query("SELECT COUNT(*) FROM notes")
    suspend fun noteCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(notes: List<Note>)

    @Delete
    suspend fun delete(note: Note)
}