package com.muradakhundov.notepad.db

import androidx.lifecycle.LiveData
import com.muradakhundov.notepad.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class NotesRepository(private val noteDao : NoteDao) {


    suspend fun getAllNotes() : List<Note> = withContext(Dispatchers.IO){
        noteDao.getAllNotes()
    }


    suspend fun insert(note : Note){
        noteDao.insert(note)
    }

    suspend fun delete(note :Note){
        noteDao.delete(note)
    }

    suspend fun update(note: Note){
        noteDao.update(note.id,note.title,note.note)
    }
}