    package com.example.noteapp

    import android.app.Activity
    import android.content.Intent
    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import android.view.View
    import android.widget.ArrayAdapter
    import android.widget.Button
    import android.widget.EditText
    import android.widget.ListView
    import androidx.appcompat.app.AlertDialog

    class MainActivity : AppCompatActivity() {
        private lateinit var notesListView: ListView
        private lateinit var notesAdapter: ArrayAdapter<String>
        private lateinit var addNoteButton: Button


        private val notesList = ArrayList<String>()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            notesListView = findViewById(R.id.notesListView)
            addNoteButton = findViewById(R.id.addNoteButton)
            addNoteButton.isAllCaps = false

            notesAdapter = ArrayAdapter(this, R.layout.custom_list_layout, notesList)
            notesListView.adapter = notesAdapter

            notesListView.setOnItemLongClickListener { _, _, position, _ ->
                showDeleteConfirmationDialog(position)
                true
            }
        }

        private fun showDeleteConfirmationDialog(position: Int) {
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle("Удаление заметки")
            alertDialogBuilder.setMessage("Вы действительно хотите удалить данную заметку?")
            alertDialogBuilder.setPositiveButton("Да") { _, _ ->

                deleteNoteAtPosition(position)
            }
            alertDialogBuilder.setNegativeButton("Нет") { dialog, _ ->

                dialog.dismiss()
            }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }

        private fun deleteNoteAtPosition(position: Int) {
            notesList.removeAt(position)
            notesAdapter.notifyDataSetChanged()
        }

        fun openAddNoteActivity(view: View) {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivityForResult(intent, ADD_NOTE_REQUEST)
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)

            if (requestCode == ADD_NOTE_REQUEST && resultCode == Activity.RESULT_OK) {
                val newNote = data?.getStringExtra("newNote")
                if (newNote != null) {
                    notesList.add(newNote)
                    notesAdapter.notifyDataSetChanged()
                }
            }
        }

        companion object {
            const val ADD_NOTE_REQUEST = 1
        }
    }