package com.example.joshuarambert.dulynoted

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView

class MainActivity : AppCompatActivity(), View.OnClickListener {


    lateinit var addButton: Button
    lateinit var notesListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addButton = findViewById(R.id.addButton)
        notesListView = findViewById(R.id.notesListView)

        //Create a click event that opens the dialog whenever the addButton is clicked
        addButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        //Inflate the add Note Dialog
        var newNoteDialog = NewNoteDialog()
        newNoteDialog.show(fragmentManager, "")
    }
}
