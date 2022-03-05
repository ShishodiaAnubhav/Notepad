package com.example.android.notepad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() , INotesRVAdapter{

    private lateinit var viewModel: NotePadViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            val intent = Intent(this, EditorActivity::class.java)
            startActivity(intent)
            finish()
        }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NotepadRVAdapter(this, this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(NotePadViewModel::class.java)

        viewModel.allNotes.observe(this, { list->
            list?.let {
                adapter.updateList(it)
            }

        })

    }

    override fun onItemDeleteClicked(notePad: NotePad) {
        viewModel.deleteNote(notePad)
        Toast.makeText(this, "${notePad.text} Deleted", Toast.LENGTH_LONG).show()
    }

    override fun onItemEditClicked(notePad: NotePad) {

        val intent = Intent(this, EditorActivity::class.java)
        intent.putExtra("text",notePad.text)
        intent.putExtra("id",notePad.id)
        startActivity(intent)
        finish()
    }
}