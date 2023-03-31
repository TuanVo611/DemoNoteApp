package com.example.notesappproject.data.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DataContentNotesDAO {

    @Query("SELECT*FROM CONTENT_NOTES_TABLE")
    fun getAll() : List<DataContentNotes>

    @Insert
    fun insertAll( dataContentNotes: DataContentNotes)
}