package com.example.notesappproject.view

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.notesappproject.R
import com.example.notesappproject.data.model.DataContentNotes
import com.example.notesappproject.databinding.ActivityEditorBinding
import com.example.notesappproject.viewmodel.ContentNotesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditorActivity : AppCompatActivity() {
    private val viewModel : ContentNotesViewModel by viewModels()
    private lateinit var binding : ActivityEditorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
    }

    private fun setupView() {
        addContentNotes()
    }

    private fun addContentNotes() {
        val titleNotes = binding.textViewTitleNotes.text.toString()
        val contentNotes = binding.textViewTitleNotes.text.toString()
        val timeNotes = System.currentTimeMillis()

        val dataContentNotes = DataContentNotes(1,titleNotes,contentNotes,timeNotes)
        val ref = this

        binding.imageSave.setOnClickListener(){
            val dialog = Dialog(this@EditorActivity)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.dialog_save_changes)
            val buttonSave = dialog.findViewById<Button>(R.id.button_save)
            val buttonDiscard = dialog.findViewById<Button>(R.id.button_discard)
            buttonDiscard.setOnClickListener(){
                dialog.cancel()
            }
            buttonSave.setOnClickListener(){
                viewModel.addContentNotes(dataContentNotes)
            }
        }
    }
}