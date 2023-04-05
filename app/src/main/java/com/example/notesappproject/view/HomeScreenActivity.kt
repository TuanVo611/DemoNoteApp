package com.example.notesappproject.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesappproject.data.model.DataContentNotes
import com.example.notesappproject.databinding.ActivityHomeScreenBinding
import com.example.notesappproject.viewmodel.ContentNotesViewModel

class HomeScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeScreenBinding
    private val viewModel: ContentNotesViewModel by viewModels()
    private lateinit var adapter: ContentNotesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
    }

    private fun setupView() {
        setDataTitles()
    }

    private fun setDataTitles() {
        viewModel.listContentNotes.observe(this) {
            val adapter =
                ContentNotesAdapter(it as ArrayList<DataContentNotes>, object : OnClickButton {
                    override fun onClick(position: Int) {
                        val titlesNotes = it[position].titleNotes
                        val contentNotes = it[position].contentNotes
                        val timeNotes = it[position].timeNotes
                        val intent = Intent(this@HomeScreenActivity, EditorActivity::class.java)
                        intent.putExtra("KEY_TITLE", titlesNotes)
                        intent.putExtra("KEY_CONTENT", contentNotes)
                        intent.putExtra("KEY_TIME", timeNotes)
                        startActivity(intent)
                    }

                    override fun onLongClick(position: Int) {
                        val idNotes = it[position].Id
                        val titlesNotes = it[position].titleNotes
                        val contentNotes = it[position].contentNotes
                        val timeNotes = it[position].timeNotes
                        val dataNotes =
                            DataContentNotes(idNotes, titlesNotes, contentNotes, timeNotes)
                        viewModel.deleteContentNotes(dataNotes)
                        Log.d("TuanVA", "onLongClick: clicked item")
                    }
                })
            val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.recycleViewHomeScreen.layoutManager = layoutManager
            binding.recycleViewHomeScreen.adapter = adapter
        }
    }
}