package com.example.notesappproject.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
    }


    private fun actionButtonAdd() {
        binding.imageButtonAdd.setOnClickListener(){
            val intent = Intent(this,EditorActivity::class.java)
            startActivity(intent)
        }
    }
}