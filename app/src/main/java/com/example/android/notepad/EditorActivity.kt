package com.example.android.notepad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

class EditorActivity : AppCompatActivity() {

    private lateinit var viewModel: NotePadViewModel
    lateinit var inputText : EditText
    lateinit var INTENT: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)
        inputText = findViewById(R.id.inputNote)

        INTENT = intent
        val note : String? = INTENT.getStringExtra("text")
        inputText.setText(note)
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NotePadViewModel::class.java)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_editor, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id == R.id.actionSave){
            val noteText = inputText.text.toString()
            if(noteText.isNotEmpty()){
                if(INTENT.getStringExtra("text") == null){
                    viewModel.insertNote(NotePad(noteText))
                    Toast.makeText(this, "Note Inserted", Toast.LENGTH_SHORT).show()
                }else{
                    viewModel.updateNote(noteText , INTENT.getIntExtra("id", 0))
                    Toast.makeText(this, "Note Updated", Toast.LENGTH_SHORT).show()
                }

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
