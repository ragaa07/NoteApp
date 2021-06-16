package com.example.noteapp.dao

import androidx.room.*
import entities.Notes

@Dao
interface NotesDao {
    
    @get:Query("SELECT * FROM notes ORDER BY id DESC")
    val allNotes:List<Notes>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(notes: Notes)
    @Delete
    fun deletNote(notes: Notes)

}