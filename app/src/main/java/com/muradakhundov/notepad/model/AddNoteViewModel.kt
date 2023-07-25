package com.muradakhundov.notepad.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muradakhundov.notepad.db.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddNoteViewModel @Inject constructor(var noteRepo : NotesRepository) : ViewModel()  {

    fun insertNote(note : Note) = viewModelScope.launch { noteRepo.insert(note) }


    fun updateNote(note : Note) = viewModelScope.launch { noteRepo.update(note) }

}