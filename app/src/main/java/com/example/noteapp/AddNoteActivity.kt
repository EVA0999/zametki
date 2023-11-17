package com.example.noteapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

class AddNoteActivity : AppCompatActivity() {
    private lateinit var newNoteEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        newNoteEditText = findViewById(R.id.newNoteEditText)

        val addButton = findViewById<Button>(R.id.addButton)
        addButton.isAllCaps = false
        addButton.setOnClickListener {
            val newNote = newNoteEditText.text.toString()
            if (newNote.isNotEmpty()) {
                val resultIntent = Intent()
                resultIntent.putExtra("newNote", newNote)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            } else {
                Toast.makeText(this, "Введите текст!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}