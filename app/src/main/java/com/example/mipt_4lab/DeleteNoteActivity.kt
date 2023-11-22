package com.example.mipt_4lab

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class DeleteNoteActivity : AppCompatActivity() {
    var listNoteItems = ArrayList<String>()
    lateinit var adapter: ArrayAdapter<String>
    lateinit var notesList: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.delete_note_activity)
        Log.d("DeleteNoteActivity", "onCreate")
        notesList=findViewById(R.id.noteSpinner)
        adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listNoteItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        notesList.adapter = adapter
        findViewById<Button>(R.id.noteDeleteButton).setOnClickListener{
            onClick()
        }
    }
    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "OnStart")
        val sharedPref = this.getSharedPreferences("file", Context.MODE_PRIVATE)
        val savedSetText = sharedPref.getStringSet("note_text", null)
        val savedSetName = sharedPref.getStringSet("note_name", null)
        if(savedSetName != null) {
            this.listNoteItems.clear()
            this.listNoteItems.addAll(savedSetName)
            this.adapter.notifyDataSetChanged()
        }
    }
    fun onClick()
    {
        val noteNameDel = notesList.selectedItem.toString()
        val sharedPref = this.getSharedPreferences("file", Context.MODE_PRIVATE)
        val savedSetText = sharedPref.getStringSet("note_text", null)
        val savedSetName = sharedPref.getStringSet("note_name", null)
        val position = savedSetName?.indexOf(noteNameDel)
        savedSetName?.remove(noteNameDel)
        savedSetText?.remove(position?.let { savedSetText.elementAt(it) })
        finish()
    }
}