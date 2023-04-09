package com.example.notesappproject.view

import android.annotation.SuppressLint
import android.app.Dialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappproject.R
import com.example.notesappproject.data.model.DataContentNotes
import com.example.notesappproject.databinding.LayoutItemNotesBinding


class ContentNotesAdapter(private val listNotes: ArrayList<DataContentNotes>,private var onClickButton: OnClickButton) :
    RecyclerView.Adapter<ContentNotesAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("ResourceAsColor")
        fun bindData(contentNotes: DataContentNotes) {
            val binding = LayoutItemNotesBinding.bind(itemView)
            binding.textViewContentNotesItem.text = contentNotes.titleNotes.toString()
            binding.backGroundDelete.isInvisible = true
            binding.textViewContentNotesItem.setOnClickListener {
                onClickButton.onClick(position = adapterPosition)
            }
            binding.textViewContentNotesItem.setOnLongClickListener {
                onClickButton.onLongClick(position = adapterPosition)
                binding.backGroundDelete.isVisible = true
                true
            }

            binding.imageViewDelete.setOnClickListener {
                onClickButton.onDeleteClick(position = adapterPosition)

                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_item_notes, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(listNotes[position])
    }

    override fun getItemCount(): Int {
        return listNotes.size
    }

    fun notifySetChanged(newList: ArrayList<DataContentNotes>) {
        val diffCallBack = DataNotesDiffCallBack(listNotes, newList)
        val diffResults = DiffUtil.calculateDiff(diffCallBack)
        diffResults.dispatchUpdatesTo(this)
        this.listNotes.clear()
        this.listNotes.addAll(newList)
    }
}