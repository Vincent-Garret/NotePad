package com.example.notepad

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(val notes: List<Note>, val itemClickListener: OnClickListener) 
    : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val cardView: CardView = itemView.findViewById<CardView>(R.id.card_view)
        val titleView: TextView = cardView.findViewById<TextView>(R.id.title)
        val contentView: TextView = cardView.findViewById<TextView>(R.id.content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_note, parent, false
            )
        return ViewHolder(viewItem)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]
        holder.cardView.setOnClickListener(itemClickListener)
        holder.cardView.tag = position
        holder.titleView.text = note.title
        holder.contentView.text = note.text
    }
}