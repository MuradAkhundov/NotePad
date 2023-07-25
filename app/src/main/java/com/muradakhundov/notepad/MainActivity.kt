package com.muradakhundov.notepad

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.SearchView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.muradakhundov.notepad.adapter.NotesAdapter
import com.muradakhundov.notepad.databinding.ActivityMainBinding
import com.muradakhundov.notepad.db.NoteDatabase
import com.muradakhundov.notepad.model.Note
import com.muradakhundov.notepad.model.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() , NotesAdapter.NotesItemClickListener,PopupMenu.OnMenuItemClickListener {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: NoteViewModel
    lateinit var adapter: NotesAdapter
    lateinit var selectedNote: Note
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(
            this)
            .get(NoteViewModel::class.java)

        viewModel.allNotes.observe(this) { list ->
           Log.e("tag","observing")
            list?.let {
                adapter.updateList(list)
                Log.e("Tag","Im in")
            }
        }

        initUI()



        setContentView(binding.root)
    }



    private fun initUI() {
        binding.recycler.setHasFixedSize(true)
        binding.recycler.layoutManager = StaggeredGridLayoutManager(2,LinearLayout.VERTICAL)
        adapter = NotesAdapter(this,this)
        binding.recycler.adapter = adapter



        binding.fabBtn.setOnClickListener{
            startActivity(Intent(this,AddNoteActivity::class.java))
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null){
                    adapter.filterList(newText)
                }
                return true
            }

        })


    }

    override fun onItemClicked(note: Note) {
        val intent = Intent(this , AddNoteActivity::class.java)
        intent.putExtra("current_note",note)
        startActivity(intent)

    }

    override fun onLongItemClicked(note: Note, cardview: CardView) {
       selectedNote = note
        popUpDisplay(cardview)
    }

    private fun popUpDisplay(cardView: CardView) {
        val popup = PopupMenu(this,cardView)
        popup.setOnMenuItemClickListener(this@MainActivity)
        popup.inflate(R.menu.pop_up_menu)
        popup.show()
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.delete_note){
            viewModel.deleteNote(selectedNote)

            return true
        }
        return false
    }


}


