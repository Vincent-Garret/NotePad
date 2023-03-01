package com.example.notepad

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.annotation.RequiresApi

class NoteDetailActivity : AppCompatActivity() {

    companion object {
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

        titleView = findViewById<TextView>(R.id.detail_title_text)
        textView = findViewById<TextView>(R.id.detail_area_text)

        titleView.text = note.title
        textView.text = note.text
    }
}