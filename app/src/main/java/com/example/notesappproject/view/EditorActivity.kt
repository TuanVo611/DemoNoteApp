package com.example.notesappproject.view

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.notesappproject.R
import com.example.notesappproject.data.model.DataContentNotes
import com.example.notesappproject.databinding.ActivityEditorBinding
import com.example.notesappproject.viewmodel.ContentNotesViewModel

class EditorActivity : AppCompatActivity() {
    private val viewModel: ContentNotesViewModel by viewModels()
    private lateinit var binding: ActivityEditorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
    }

    private fun setupView() {
        addContentNotes()
        buttonBackAction()
    }

    private fun buttonBackAction() {
        binding.buttonBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addContentNotes() {
        binding.imageViewBackgroundSave.setOnClickListener {
            val dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.dialog_save_changes)
            val buttonSave = dialog.findViewById(R.id.button_save) as Button
            val buttonDiscard = dialog.findViewById(R.id.button_discard) as Button
            buttonDiscard.setOnClickListener {
                dialog.dismiss()
            }
            buttonSave.setOnClickListener {
                val titleNotes = binding.editTextTitle.text.toString()
                val contentNotes = binding.editTextContent.text.toString()
                val timeNotes = System.currentTimeMillis()
                val dataContentNotes = DataContentNotes(0, titleNotes, contentNotes, timeNotes)
                viewModel.addContentNotes(dataContentNotes).observe(this) {
                    Log.d("TuanVA", "addContentNotes1: $dataContentNotes")
                }
                dialog.dismiss()
                val intent = Intent(this, HomeScreenActivity::class.java)
                startActivity(intent)
            }
            dialog.show()
        }
    }
}