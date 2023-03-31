package com.example.notesappproject.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesappproject.data.model.AppDataBase
import com.example.notesappproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
    }

    private fun setupView() {
        actionButtonAdd()
        setupRoomDatabase()
    }

    private fun setupRoomDatabase() {
        val database = Room.databaseBuilder(applicationContext,AppDataBase::class.java,"DataBaseNotes").build()

        val dataContentNotesDAO = database.dataContentNotesDAO()
        val listContentNotes = dataContentNotesDAO.getAll()


    }

    private fun actionButtonAdd() {
        binding.imageButtonAdd.setOnClickListener(){
            val intent = Intent(this,EditorActivity::class.java)
            startActivity(intent)
        }
    }
}