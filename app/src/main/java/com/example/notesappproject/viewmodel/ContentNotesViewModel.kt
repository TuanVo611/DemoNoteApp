package com.example.notesappproject.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.notesappproject.data.model.DataContentNotes
import com.example.notesappproject.data.repository.ContentNotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContentNotesViewModel : ViewModel() {
    private val repository = ContentNotesRepository()
    private val _listContentNotes = MediatorLiveData<List<DataContentNotes>>()
    val listContentNotes: LiveData<List<DataContentNotes>>
        get() = _listContentNotes

    init {
        getListContentNotes()
    }

    fun getListContentNotes() {
        viewModelScope.launch(Dispatchers.Main) {
            _listContentNotes.addSource(repository.getListContentNotes()) {
                _listContentNotes.value = it
            }
        }
    }

    fun addContentNotes(dataContentNotes: DataContentNotes): LiveData<Unit> {
        val result = MutableLiveData<Unit>()
        viewModelScope.launch(Dispatchers.Main) {
            val data = repository.addContentNotes(dataContentNotes)
            result.value = data.value
            Log.d("TuanVA", "addContentNotes2: $dataContentNotes")
        }
        return result

    }

    fun deleteContentNotes(dataContentNotes: DataContentNotes): LiveData<Unit> {
        val result = MutableLiveData<Unit>()
        viewModelScope.launch(Dispatchers.Main) {
            val data = repository.deleteContentNotes(dataContentNotes)
            result.value = data.value
        }
        return result
    }
}