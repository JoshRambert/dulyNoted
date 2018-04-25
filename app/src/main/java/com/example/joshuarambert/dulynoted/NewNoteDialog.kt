package com.example.joshuarambert.dulynoted

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

/**
 * Created by joshuarambert on 4/24/18.
 */

class NewNoteDialog : DialogFragment(), View.OnClickListener {


    lateinit var cancelButton: Button
    lateinit var okButton: Button
    lateinit var titleText: EditText
    lateinit var contentText: EditText

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //This is the dialog window
        var builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        var inflater: LayoutInflater = activity.layoutInflater
        var dialogView: View = inflater.inflate(R.layout.new_note_dialog, null)

        //get a reference to each of the elements within the view
        cancelButton = dialogView.findViewById(R.id.cancelButton)
        okButton = dialogView.findViewById(R.id.okButton)
        titleText = dialogView.findViewById(R.id.titleText)
        contentText = dialogView.findViewById(R.id.contentText)

        //Create the dialog window
        builder.setView(dialogView).setMessage("Add a New Note")

        //Create an onclick for the cancel button and the
        cancelButton.setOnClickListener(View.OnClickListener {
            dismiss()
        })

        //create an onClick for the OKay button that will write it to the firebase
        okButton.setOnClickListener(View.OnClickListener {
            //Take what is in the EditTExt and assign it to the note class
            var title = titleText.text.toString()
            var content = contentText.text.toString()
            var note = Note(title, content)

            //Push the note to the database with the references
            var currentUser = FirebaseAuth.getInstance().currentUser
            var mRootRef = FirebaseDatabase.getInstance().reference
            var mUserRef = mRootRef.child(currentUser!!.uid)

            mUserRef.push().setValue(note)
        })

        return builder.create()
    }

    override fun onClick(v: View?) {

    }
}