package com.example.notesappproject.view

interface OnClickButton {
    fun onClick(position: Int)
    fun onLongClick(position: Int)
    fun onDeleteClick(position: Int)
}