package com.example.noteapp.dao

import androidx.room.*
import entities.Notes

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes ORDER BY id DESC")
    suspend fun getallNotes():List<Notes>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertNote(notes: Notes)
    @Delete
    suspend fun deletNote(notes: Notes)

}