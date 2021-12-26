package com.example.declutteringapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Space::class), version = 1, exportSchema = false
)

abstract class SpaceDataBase : RoomDatabase(){

        abstract fun getSpaceDao(): MySpacesDao

        companion object {

            @Volatile
            private var INSTANCE: SpaceDataBase? = null

            fun getDatabase(context: Context): SpaceDataBase {

                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        SpaceDataBase::class.java,
                        "space_database"
                    ).build()
                    INSTANCE = instance
                    instance
                }
            }
        }
    }

