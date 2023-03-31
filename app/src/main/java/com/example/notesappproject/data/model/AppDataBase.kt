package com.example.notesappproject.data.model

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DataContentNotes::class], version = 2, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun dataContentNotesDAO(): DataContentNotesDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabaseClient(): AppDataBase {
            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {
                INSTANCE = Room.databaseBuilder(MyApplication.getInstance().applicationContext,
                    AppDataBase::class.java,
                    "AppDatabase").fallbackToDestructiveMigration().build()

                return INSTANCE!!
            }
        }
    }
}