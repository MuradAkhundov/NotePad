package com.muradakhundov.notepad.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.muradakhundov.notepad.model.Note


@Dao
interface NoteDao {


    //IF INSERTED NODE ALREADY EXIST , THEN JUST REPLACE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note : Note)

    @Delete
    suspend fun delete(note : Note)

    @Query("Select * from notes_table order by id ASC")
    fun getAllNotes() : List<Note>

    @Query("UPDATE notes_table Set title = :title , note = :note WHERE id = :id")
    suspend fun update(id : Int? , title: String? , note : String?)
}