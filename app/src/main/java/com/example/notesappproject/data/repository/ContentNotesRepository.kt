package com.example.notesappproject.data.repository

import androidx.lifecycle.LiveData
import com.example.notesappproject.data.datasource.ContentNotesDataSource
import com.example.notesappproject.data.model.DataContentNotes

class ContentNotesRepository {

    private val dataSource = ContentNotesDataSource()
    suspend fun getListContentNotes(): LiveData<List<DataContentNotes>> {
        return dataSource.getListContentNotes()
    }

    suspend fun addContentNotes(dataContentNotes: com.example.notesappproject.data.model.DataContentNotes) =
        dataSource.addContentNotes(dataContentNotes)

    suspend fun deleteContentNotes(dataContentNotes: com.example.notesappproject.data.model.DataContentNotes) =
        dataSource.deleteContentNotes(dataContentNotes)
}