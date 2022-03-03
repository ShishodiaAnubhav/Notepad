package com.example.android.notepad

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities =  arrayOf(NotePad::class), version = 1, exportSchema = false)
abstract class NotePadDatabase: RoomDatabase() {

    abstract fun getNotePadDoa(): NotePadDao

    companion object{

        @Volatile
        private var INSTANCE: NotePadDatabase? = null

        fun getDatabase(context: Context): NotePadDatabase {
            //if the INSTANCE is not null, then return it,
            //if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotePadDatabase::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance
                //return instance
                instance
            }
        }
    }
}