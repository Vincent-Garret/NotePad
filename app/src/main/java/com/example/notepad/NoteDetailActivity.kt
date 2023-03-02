package com.example.notepad

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.annotation.RequiresApi

class NoteDetailActivity : AppCompatActivity() {

    companion object {
        val REQUEST_EDIT_NOTE = 1
        val EXTRA_NOTE = "note"
        val EXTRA_NOTE_INDEX = "noteIndex"
    }

    lateinit var note: Note
    var noteIndex: Int = 1

    lateinit var titleView: TextView
    lateinit var  textView: TextView


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)
        //note = intent.getParcelableExtra(EXTRA_NOTE, Note::class.java)!!
        //note = intent.getParcelableExtra<Note>(EXTRA_NOTE)!!
        note = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Log.i("Mon Probleme", "JE SUIS TIRAMISU")
            intent.getParcelableExtra(EXTRA_NOTE, Note::class.java)!!}
        else {
            Log.i("Mon Probleme", "JE SUIS DEPRECIATED")
            intent.getParcelableExtra<Note>(EXTRA_NOTE)!!
        }

        noteIndex = intent.getIntExtra(EXTRA_NOTE_INDEX, -1)

        titleView = findViewById<TextView>(R.id.detail_title_text)
        textView = findViewById<TextView>(R.id.detail_area_text)

        titleView.text = note.title
        textView.text = note.text


    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {    menuInflater.inflate(R.menu.activity_note_detail, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        return when(item.itemId) {
        R.id.action_save -> {
            saveNote()
            true        }
        else -> super.onOptionsItemSelected(item)
        }
    }

    fun saveNote() {
        note.title = titleView.text.toString()
        note.text = titleView.text.toString()

        intent = Intent()
        intent.putExtra(EXTRA_NOTE, note)
        intent.putExtra(EXTRA_NOTE_INDEX, noteIndex)
        setResult(Activity.RESULT_OK, intent)
        finish()

    }
}