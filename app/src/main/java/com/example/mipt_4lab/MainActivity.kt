package com.example.mipt_4lab

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    var listNoteItems = ArrayList<String>()
    lateinit var adapter: ArrayAdapter<String>
    lateinit var notesList: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        Log.d("MainActivity", "onCreate")
        this.notesList=findViewById(R.id.notesList)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listNoteItems)
        this.notesList.setAdapter(adapter)
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
        this.notesList.setOnItemClickListener { parent: AdapterView<*>, view, position, id ->

            val noteTextDraw = savedSetText?.elementAt(position)
            Snackbar.make(notesList, "$noteTextDraw", 2400).setTextMaxLines(100).show()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        Log.d("MainActivity", "onCreateOptionsMenu")
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.notes_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("MainActivity", "onOptionsItemSelected")
        return when (item.itemId)
        {
            R.id.add_note -> {
                val intent = Intent(this, AddNoteActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.remove_note -> {
                val intent = Intent(this, DeleteNoteActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}