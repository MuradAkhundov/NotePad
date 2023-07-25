package com.muradakhundov.notepad.hilt

import android.app.Application
import android.content.Context
import android.provider.CalendarContract.Instances
import androidx.room.Room
import com.muradakhundov.notepad.db.NoteDao
import com.muradakhundov.notepad.db.NoteDatabase
import com.muradakhundov.notepad.db.NotesRepository
import com.muradakhundov.notepad.util.Constant.Companion.DATABASE_NAME
import dagger.Provides
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@HiltAndroidApp
class HiltApplication : Application() {



}