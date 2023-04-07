package com.example.notesappproject.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import com.example.notesappproject.data.model.DataContentNotes
import com.example.notesappproject.databinding.ActivityMainBinding
import com.example.notesappproject.viewmodel.ContentNotesViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel : ContentNotesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        setupView()
    }

    private fun setupView() {
        actionButtonAdd()
        displayList()
    }

    private fun displayList() {
        viewModel.listContentNotes.observe(this){
            if (it != null && it.isNotEmpty()) {
                Log.d("Tuan", "displayList: abc")
                val intent = Intent(this, HomeScreenActivity::class.java)
                startActivity(intent)
            }
        }
    }


    private fun actionButtonAdd() {
        binding.imageButtonAdd.setOnClickListener() {
            val intent = Intent(this, EditorActivity::class.java)
            startActivity(intent)
        }
    }
}