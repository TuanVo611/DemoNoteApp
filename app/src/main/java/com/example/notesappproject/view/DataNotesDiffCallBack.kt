package com.example.notesappproject.view

import androidx.recyclerview.widget.DiffUtil
import com.example.notesappproject.data.model.DataContentNotes

class DataNotesDiffCallBack(private val oldList: List<DataContentNotes>,
                            private val newList: List<DataContentNotes>) : DiffUtil.Callback(){
    override fun getOldListSize(): Int {
        return  oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].Id == newList[newItemPosition].Id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return  oldList[oldItemPosition].titleNotes != newList[newItemPosition].titleNotes
                &&  oldList[oldItemPosition].contentNotes != newList[newItemPosition].contentNotes
    }
}