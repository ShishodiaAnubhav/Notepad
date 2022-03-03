package com.example.android.notepad

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotepadRVAdapter(private val context: Context, private val listener: INotesRVAdapter) : RecyclerView.Adapter<NotepadRVAdapter.NotepadViewHolder>() {

    private val allNotes = ArrayList<NotePad>()

    inner class NotepadViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textView: TextView = itemView.findViewById(R.id.text)
        val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)
        val editButton: ImageView = itemView.findViewById(R.id.editButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotepadViewHolder {
        val viewHolder = NotepadViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note, parent, false))
        viewHolder.deleteButton.setOnClickListener {
            listener.onItemDeleteClicked(allNotes[viewHolder.adapterPosition])
        }
        viewHolder.editButton.setOnClickListener {
            listener.onItemEditClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NotepadViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.textView.text = currentNote.text
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList: List<NotePad>) {
        allNotes.clear()
        allNotes.addAll(newList)

        notifyDataSetChanged()
    }
}

interface  INotesRVAdapter {
    fun onItemDeleteClicked(notePad: NotePad)
    fun onItemEditClicked(notePad: NotePad)
}