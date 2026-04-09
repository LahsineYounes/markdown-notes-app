package com.example.markdownnotesapp

// A class who connects UI ↔ data
// Holds the note list, reacts to search, calls DAO
import android.app.Application
import androidx.lifecycle.*
import com.example.markdownnotesapp.data.NoteDatabase
import com.example.markdownnotesapp.data.SampleNotes
import com.example.markdownnotesapp.model.Note
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class NoteViewModel(app: Application) : AndroidViewModel(app) {
    private val dao = NoteDatabase.getDatabase(app).noteDao()

    init {
        viewModelScope.launch {
            if (dao.noteCount() == 0) {
                val now = System.currentTimeMillis()
                val withStaggeredTimes = SampleNotes.examples.mapIndexed { index, note ->
                    note.copy(timestamp = now - index * 1000L)
                }
                dao.insertAll(withStaggeredTimes)
            }
        }
    }

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    // Switches between all notes and search results automatically
    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val notes: StateFlow<List<Note>> = _searchQuery
        .debounce(300)
        .flatMapLatest { query ->
            if (query.isBlank()) dao.getAllNotes()
            else dao.searchNotes(query)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun setSearch(query: String) { _searchQuery.value = query }

    fun addOrUpdate(note: Note) = viewModelScope.launch { dao.insert(note) }

    fun delete(note: Note) = viewModelScope.launch { dao.delete(note) }
}