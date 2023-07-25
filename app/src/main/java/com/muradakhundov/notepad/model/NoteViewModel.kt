package com.muradakhundov.notepad.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muradakhundov.notepad.db.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NoteViewModel @Inject constructor(var nrepo : NotesRepository) :ViewModel(){

   val allNotes  = MutableLiveData<List<Note>>()


    init {
        loadNotes()
    }


    fun loadNotes() {
        CoroutineScope(Dispatchers.Main).launch {
            allNotes.value = nrepo.getAllNotes()
        }
    }


    fun deleteNote(note : Note) = viewModelScope.launch { nrepo.delete(note)
    loadNotes()}

}