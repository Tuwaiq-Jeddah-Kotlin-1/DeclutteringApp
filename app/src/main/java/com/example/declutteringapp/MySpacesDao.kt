package com.example.declutteringapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MySpacesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(space :Space)


    @Delete
    suspend fun delete(space :Space)


    @Query("Select * from spaceTable order by id ASC")
    fun getAllSpaces(): LiveData<List<Space>>

    @Update
    suspend fun update(space :Space)

}


