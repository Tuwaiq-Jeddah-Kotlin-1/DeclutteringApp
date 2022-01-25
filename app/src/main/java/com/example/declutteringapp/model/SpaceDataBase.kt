package com.example.declutteringapp.model

import android.content.Context
import androidx.room.*

@Database(entities = arrayOf(
    Space::class,
    ToDeclutter::class,ThirtyDays::class) , version = 1, exportSchema = false)


abstract class SpaceDataBase : RoomDatabase(){

    abstract fun getSpaceDao(): MySpacesDao


    companion object {

        @Volatile
        private var INSTANCE: SpaceDataBase? = null

        fun getDatabase(context: Context?): SpaceDataBase {

            return (INSTANCE ?: synchronized(this) {
                val instance = context?.let {
                    Room.databaseBuilder(
                        it,
                        SpaceDataBase::class.java,
                        "space_database"

                    ).build()
                }
                INSTANCE = instance
                instance
            })!!
        }
    }
}
