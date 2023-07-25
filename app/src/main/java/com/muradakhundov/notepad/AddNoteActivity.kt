package com.muradakhundov.notepad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.muradakhundov.notepad.databinding.ActivityAddNoteBinding
import com.muradakhundov.notepad.model.AddNoteViewModel
import com.muradakhundov.notepad.model.Note
import com.muradakhundov.notepad.model.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date


@AndroidEntryPoint
class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddNoteBinding
    private lateinit var note : Note
    private lateinit var viewModel: AddNoteViewModel
    private lateinit var old_note : Note
    var isUpdate = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddNoteBinding.inflate(layoutInflater)




        viewModel = ViewModelProvider(this).get(AddNoteViewModel::class.java)

        binding.backBtn.setOnClickListener {
            onBackPressed()
        }
        try {
          old_note = intent.getSerializableExtra("current_note") as Note
          binding.editTextTitle.setText(old_note.title)
          binding.editTextNote.setText(old_note.note)
          isUpdate = true
        }catch (e : Exception){
            e.printStackTrace()
        }

        binding.saveBtn.setOnClickListener {
            val title = binding.editTextTitle.text.toString()
            val noteDesc = binding.editTextNote.text.toString()

            if (title.isNotEmpty() || noteDesc.isNotEmpty()){
                val formatter = SimpleDateFormat("EEE, d MMM yyyy HH:mm a")

                if (isUpdate){
                    note = Note(
                        old_note.id,title,noteDesc,formatter.format(Date())
                    )
                    viewModel.updateNote(note)
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)

                }
                else{
                    note = Note(
                        null,title,noteDesc,formatter.format(Date())
                    )
                    val intent = Intent(this,MainActivity::class.java)
                    viewModel.insertNote(note)
                    startActivity(intent)
                    Log.e("TAG","Insertion")

                }
            }
            else{
                Toast.makeText(this,"Please enter note",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }




        setContentView(binding.root)
    }

    override fun onBackPressed() {

    }
}