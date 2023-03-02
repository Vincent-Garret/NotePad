package com.example.notepad

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NoteListActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var notes:MutableList<Note>
    lateinit var adapter: NoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode != Activity.RESULT_OK || data == null)
        {
            return
        }
        when(requestCode) {
            NoteDetailActivity.REQUEST_EDIT_NOTE -> processEditNoteResult(data)
        }
    }

    private fun processEditNoteResult(data: Intent) {
        val noteIndex = data.getIntExtra(NoteDetailActivity.EXTRA_NOTE_INDEX, -1)
        val note = data.getParcelableExtra<Note>(NoteDetailActivity.EXTRA_NOTE)
        saveNote(note, noteIndex)
    }

    private fun saveNote(note: Note?, noteIndex: Int) {
        if (note != null) {
            notes[noteIndex] = note
        }
        adapter.notifyDataSetChanged()
    }

    override fun onClick(view: View) {
        if(view.tag != null) {
            //Log.i("no activity", "note")
            showNoteDetail(view.tag as Int)
        }
    }

    fun showNoteDetail(noteIndex: Int) {
        val note = notes[noteIndex]

        val intent = Intent(this, NoteDetailActivity::class.java)
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE, note)
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE_INDEX, noteIndex)
        //startActivity(intent)
        startActivityForResult(intent, NoteDetailActivity.REQUEST_EDIT_NOTE)

    }
}