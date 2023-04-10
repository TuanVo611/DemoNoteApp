package com.example.notesappproject.view

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesappproject.R
import com.example.notesappproject.data.model.DataContentNotes
import com.example.notesappproject.databinding.ActivityMainBinding
import com.example.notesappproject.databinding.LayoutItemNotesBinding
import com.example.notesappproject.viewmodel.ContentNotesViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var bindingItem: LayoutItemNotesBinding
    private val viewModel: ContentNotesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        bindingItem = LayoutItemNotesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        setupView()
        bindingItem.backGroundDelete.isInvisible = true
    }

    private fun setupView() {
        actionButtonAdd()
        displayList()
        setDataTitles()
    }

    private fun displayList() {
        viewModel.listContentNotes.observe(this) {
            if (it != null && it.isNotEmpty()) {
                binding.imageViewMain.visibility = View.GONE
                binding.textViewMain.visibility = View.GONE
                binding.recycleViewHomeScreen.visibility = View.VISIBLE
            }
        }
    }


    private fun actionButtonAdd() {
        binding.imageButtonAdd.setOnClickListener {
            val intent = Intent(this, EditorActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setDataTitles() {
        viewModel.listContentNotes.observe(this) {
            val adapter =
                ContentNotesAdapter(it as ArrayList<DataContentNotes>, object : OnClickButton {
                    override fun onClick(position: Int) {
                        val titlesNotes = it[position].titleNotes
                        val contentNotes = it[position].contentNotes
                        val timeNotes = it[position].timeNotes
                        val intent = Intent(this@MainActivity, EditorActivity::class.java)
                        intent.putExtra("KEY_TITLE", titlesNotes)
                        intent.putExtra("KEY_CONTENT", contentNotes)
                        intent.putExtra("KEY_TIME", timeNotes)
                        startActivity(intent)
                    }

                    @SuppressLint("ResourceAsColor")
                    override fun onLongClick(position: Int) {
                    }

                    override fun onDeleteClick(position: Int) {
                        val idNotes = it[position].Id
                        val titlesNotes = it[position].titleNotes
                        val contentNotes = it[position].contentNotes
                        val timeNotes = it[position].timeNotes
                        val dataNotes =
                            DataContentNotes(idNotes, titlesNotes, contentNotes, timeNotes)
                        val dialog = Dialog(bindingItem.imageViewDelete.context)
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                        dialog.setCancelable(false)
                        dialog.setContentView(R.layout.dialog_save_changes)
                        val buttonSave = dialog.findViewById(R.id.button_save) as Button
                        val buttonDiscard = dialog.findViewById(R.id.button_discard) as Button
                        buttonDiscard.setOnClickListener {
                            dialog.dismiss()
                        }
                        buttonSave.setOnClickListener {
                            viewModel.deleteContentNotes(dataNotes).observe(this@MainActivity) {
                                viewModel.getListContentNotes()
                            }
                            dialog.dismiss()
                        }
                        dialog.show()
                    }
                })
            val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.recycleViewHomeScreen.layoutManager = layoutManager
            binding.recycleViewHomeScreen.adapter = adapter
        }
    }
}