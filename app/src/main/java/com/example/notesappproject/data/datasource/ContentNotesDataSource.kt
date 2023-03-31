package com.example.notesappproject.data.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notesappproject.data.model.AppDataBase
import com.example.notesappproject.data.model.DataContentNotes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class ContentNotesDataSource : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO
    suspend fun getListContentNotes(): LiveData<List<DataContentNotes>> {
        val listContentNotes = withContext(Dispatchers.IO){
            AppDataBase.getDatabaseClient().dataContentNotesDAO().getAll()
        }
        val result = MutableLiveData<List<DataContentNotes>>()
        result.value = listContentNotes
        return result
    }

    suspend fun addContentNotes(dataContentNotes: DataContentNotes) : LiveData<Unit> {
        val dataAddContentNotes = withContext(Dispatchers.IO){
            AppDataBase.getDatabaseClient().dataContentNotesDAO().insertAll(dataContentNotes)
        }
        val result = MutableLiveData<Unit>()
        result.value = dataAddContentNotes
        return result
    }
}