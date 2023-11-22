package com.example.mipt_4lab

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class AddNoteActivity : AppCompatActivity() {
    lateinit var edName: EditText
    lateinit var edText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_note_activity)
        Log.d("AddNoteActivity", "OnCreate")
        this.edName = findViewById(R.id.noteName)
        this.edText = findViewById(R.id.noteText)
        findViewById<Button>(R.id.noteSave).setOnClickListener{
            onClick()
        }
    }
    fun onClick()
    {
        val nameToAdd = this.edName.getText().toString()
        val textToAdd = this.edText.getText().toString()
        val sharedPref = this.getSharedPreferences("file", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        val savedSetText = sharedPref.getStringSet("note_text", null)
        val savedSetName = sharedPref.getStringSet("note_name", null)
        val newMuSetText = mutableSetOf<String>()
        val newMuSetName= mutableSetOf<String>()
        if(savedSetName !=null && savedSetText !=null)
        {
            newMuSetText.addAll(savedSetText)
            newMuSetName.addAll(savedSetName)
        }
        newMuSetText.add(textToAdd)
        newMuSetName.add(nameToAdd)

        val newSetName: Set<String> = newMuSetName.toSet()
        val newSetText: Set<String> = newMuSetText.toSet()
        editor.putStringSet("note_text", newSetText)
        editor.putStringSet("note_name", newSetName)
        editor.apply()
        finish()
    }
}