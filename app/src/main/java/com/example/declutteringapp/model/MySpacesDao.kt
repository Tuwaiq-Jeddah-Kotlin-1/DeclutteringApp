package com.example.declutteringapp.model

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface MySpacesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: ToDeclutter)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(space: Space)

    @Delete
    suspend fun delete(item:ToDeclutter)

    @Delete
    suspend fun delete(space:Space)

    @Query("Select * from declutterTable WHERE roomId = (:roomId) ")
    fun getAllItems(roomId: Int): LiveData<List<ToDeclutter>>

    @Query("Select * from spaceTable order by roomId ASC")
    fun getAllSpacesItems(): LiveData<List<Space>>

    @Update
    suspend fun update(item: ToDeclutter)

    @Update
    suspend fun update(space: Space)


    @Query("Select * from  spaceTable WHERE  roomId")
    fun getAllSpaces(): LiveData<List<Space>>

   @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun insert(thirtyDays: ThirtyDays)
   @Delete
    suspend fun delete(thirtyDays: ThirtyDays)

  @Query("Select * from daysTable order by id ASC")
fun getAllDays(): LiveData<List<ThirtyDays>>

  @Update
  suspend fun update(thirtyDays: ThirtyDays)

}