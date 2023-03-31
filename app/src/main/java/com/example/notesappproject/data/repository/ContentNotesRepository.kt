package com.example.notesappproject.data.repository

import com.example.notesappproject.data.datasource.ContentNotesDataSource

class ContentNotesRepository {

    private val dataSource = ContentNotesDataSource()
    suspend fun getListContentNotes() = dataSource.getListContentNotes()
    suspend fun addContentNotes(dataContentNotes: com.example.notesappproject.data.model.DataContentNotes) =
        dataSource.addContentNotes(dataContentNotes)
}