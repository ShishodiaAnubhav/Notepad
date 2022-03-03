package com.example.android.notepad

import androidx.lifecycle.LiveData

class NotePadRepo(private val notePadDao: NotePadDao) {
    val allNotes: LiveData<List<NotePad>> = notePadDao.getAllNotes()

    suspend fun insert(notePad: NotePad){
        notePadDao.insert(notePad)
    }

    suspend fun delete(notePad: NotePad){
        notePadDao.delete(notePad)
    }

    suspend fun update(text :String , id: Int){
        notePadDao.update(text , id)
    }
}