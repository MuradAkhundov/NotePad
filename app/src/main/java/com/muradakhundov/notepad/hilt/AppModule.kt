package com.muradakhundov.notepad.hilt

import android.content.Context
import androidx.room.Room
import com.muradakhundov.notepad.db.NoteDao
import com.muradakhundov.notepad.db.NoteDatabase
import com.muradakhundov.notepad.db.NotesRepository
import com.muradakhundov.notepad.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Provides
    @Singleton
    fun provideNotesRepository(ndao : NoteDao) : NotesRepository {
        return  NotesRepository(ndao)
    }

    @Provides
    @Singleton
    fun provideNotesDao(@ApplicationContext context : Context) : NoteDao {
        val db = Room.databaseBuilder(context , NoteDatabase :: class.java, Constant.DATABASE_NAME).build()
        return db.getNoteDao()
    }
}