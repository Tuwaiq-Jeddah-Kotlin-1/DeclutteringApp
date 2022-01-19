package com.example.declutteringapp.model

import android.content.Context
import androidx.room.*
import com.example.declutteringapp.model.repo.MySpacesDao

@Database(entities = arrayOf(
    Space::class,
    ToDeclutter::class,Score::class) , version = 1, exportSchema = false)


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

