package com.example.android.notepad

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotePadDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(notePad: NotePad)

    @Delete
    suspend fun delete(notePad: NotePad)

    @Query("UPDATE notes_table SET text= :note WHERE id = :noteId")
    suspend fun update(note : String, noteId : Int)


    @Query("SELECT * FROM notes_table ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<NotePad>>
}