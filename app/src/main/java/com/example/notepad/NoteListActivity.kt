package com.example.notepad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NoteListActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var notes:MutableList<Note>
    lateinit var adapter: NoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        notes = mutableListOf<Note>()
        notes.add(Note("Note 1", "test de note 1"))
        notes.add(Note("Note 2", "test de note 2"))
        notes.add(Note("Note 3", "test de note 3"))
        notes.add(Note("Note 4", "test de note 4"))
        notes.add(Note("Note 5", "test de note 5"))

        adapter = NoteAdapter(notes, this)

        val recyclerView = findViewById<RecyclerView>(R.id.notes_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onClick(view: View) {
        if(view.tag != null) {
            Log.i("no activity", "note")
        }
    }
}