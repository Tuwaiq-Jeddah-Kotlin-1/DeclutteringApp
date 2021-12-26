package com.example.declutteringapp


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(ThirtyDays::class), version = 1, exportSchema = false)
abstract class ThirtyDaysDP : RoomDatabase() {

    abstract fun getDaysDao(): ThirtyDaysDao

    companion object {

        @Volatile
        private var INSTANCE: ThirtyDaysDP? = null

        fun getDatabase(context: Context): ThirtyDaysDP {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ThirtyDaysDP::class.java,
                    "day_database").allowMainThreadQueries()
                .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}