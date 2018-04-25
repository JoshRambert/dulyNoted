package com.example.joshuarambert.dulynoted

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity(), View.OnClickListener {


    lateinit var addButton: Button
    lateinit var notesListView: ListView
    lateinit var noteList: MutableList<Note>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addButton = findViewById(R.id.addButton)
        notesListView = findViewById(R.id.notesListView)
        noteList = mutableListOf()

        //Create a click event that opens the dialog whenever the addButton is clicked
        addButton.setOnClickListener(this)

        //Get the data from the database and store it within this array
        var mRootRef = FirebaseDatabase.getInstance().reference
        var mUserRef = mRootRef.child("user_one")

        //Get the data from the database store it into an array then call the List adapater class
        mUserRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot?) {
                // get the data
                if (p0!!.exists()){
                    for (n in p0.children){
                        val note = n.getValue(Note::class.java)
                            noteList.add(note!!)
                    }

                    val adapterList = NotesAdapterClass(noteList, applicationContext)
                    notesListView.adapter = adapterList
                }
            }

            override fun onCancelled(p0: DatabaseError?) {

            }
        })
    }

    override fun onClick(v: View?) {
        //Inflate the add Note Dialog
        var newNoteDialog = NewNoteDialog()
        newNoteDialog.show(fragmentManager, "")
    }

    //create a class for the adapter
    class NotesAdapterClass(val notesList: List<Note>, val mContext: Context) : BaseAdapter(){
        //get a reference for all of the properties
        lateinit var contentTxt: TextView
        lateinit var titleTxt: TextView

        override fun getCount(): Int {
            return notesList.count()
        }

        override fun getItemId(position: Int): Long {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getItem(position: Int): Any {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            var inflater: LayoutInflater = LayoutInflater.from(mContext)
            var view: View = inflater.inflate(R.layout.list_item, null)

            //initialize the properties from the view
            titleTxt = view.findViewById(R.id.noteTitle)
            contentTxt = view.findViewById(R.id.noteContent)

            val note = notesList[position]
            titleTxt.text = note.title
            contentTxt.text = note.content

            return view
        }
    }
}