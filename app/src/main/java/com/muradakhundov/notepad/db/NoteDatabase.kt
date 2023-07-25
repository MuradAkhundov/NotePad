package com.muradakhundov.notepad.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.muradakhundov.notepad.model.Note
import com.muradakhundov.notepad.util.Constant.Companion.DATABASE_NAME

@Database(entities = arrayOf(Note::class), version = 1 , exportSchema = false)
abstract class NoteDatabase : RoomDatabase(){

    abstract fun getNoteDao() : NoteDao



}