package com.example.android.notepad

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotePadViewModel(application: Application) : AndroidViewModel(application) {
    val allNotes: LiveData<List<NotePad>>
    private val repository: NotePadRepo

    init {
        val dao = NotePadDatabase.getDatabase(application).getNotePadDoa()
        repository = NotePadRepo(dao)
        allNotes = repository.allNotes
    }

    fun deleteNote(notePad: NotePad) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(notePad)
    }

    fun insertNote(notePad: NotePad) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(notePad)
    }

    fun updateNote(text :String , id: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(text , id)
    }
}