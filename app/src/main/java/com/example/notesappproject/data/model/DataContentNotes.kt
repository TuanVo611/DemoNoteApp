package com.example.notesappproject.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CONTENT_NOTES_TABLE")
data class DataContentNotes (
    @PrimaryKey(autoGenerate = true) val Id : Int,
    @NonNull val titleNotes : String?,
    @NonNull val contentNotes: String?,
    @NonNull val timeNotes : Long?
)
