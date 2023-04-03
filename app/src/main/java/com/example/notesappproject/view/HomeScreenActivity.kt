package com.example.notesappproject.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesappproject.R
import com.example.notesappproject.data.model.DataContentNotes
import com.example.notesappproject.databinding.ActivityHomeScreenBinding
import com.example.notesappproject.databinding.ActivityMainBinding
import com.example.notesappproject.viewmodel.ContentNotesViewModel

class HomeScreenActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeScreenBinding
    private val viewModel : ContentNotesViewModel by viewModels()
    private val listData = ArrayList<DataContentNotes>()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
    }

    private fun setupView() {
        setDataTitles()
        deleteContent()
    }

    private fun deleteContent() {


    }

    private fun setDataTitles() {
        viewModel.listContentNotes.observe(this){
            val adapter =ContentNotesAdapter(it as ArrayList<DataContentNotes>,object: OnClickButton{
                override fun onClick(position: Int) {
                    val idNotes = it[position].Id
                    val titlesNotes = it[position].titleNotes
                    val contentNotes = it[position].contentNotes
                    val timeNotes = it[position].timeNotes
                    val dataNotes = DataContentNotes(idNotes,titlesNotes,contentNotes,timeNotes)
                }
            })
            val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.recycleViewHomeScreen.layoutManager = layoutManager
            binding.recycleViewHomeScreen.adapter = adapter
        }
    }
}