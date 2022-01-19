package com.example.declutteringapp.model.repo

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.declutteringapp.model.Score
import com.example.declutteringapp.model.Space
import com.example.declutteringapp.model.ToDeclutter

@Dao
interface MySpacesDao {


  /*  @Transaction
    @Query("SELECT * FROM spaceTable WHERE roomId = :roomId")
    fun getAllItemss(roomId: Int): LiveData<List<SpaceItemRelation>>*/


  /*  @Delete
    suspend fun delete(spaceItemRelation: SpaceItemRelation)
*/
/*
    @Transaction
    @Query("SELECT * FROM spaceTable")
    fun getAllItemsToDeclutter(): LiveData<List<SpaceItemBoth>>
*/

  /*  @Transaction
    @Query("SELECT * FROM declutterTable")
    fun getGuestWithReservations(): LiveData<List<ItemsWithSpaces>>*/

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(score: Score)


     @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: ToDeclutter)

  /*  @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(spaceItem: SpaceItemBoth)
*/

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(space: Space)


    @Delete
    suspend fun delete(item: ToDeclutter)

    @Delete
    suspend fun delete(space: Space)


    @Query("Select * from declutterTable WHERE roomId = (:roomId) ")
    fun getAllItems(roomId: Int): LiveData<List<ToDeclutter>>

    @Query("Select * from scoreTable order by id ASC ")
    fun getAcores(): LiveData<List<Score>>


    @Query("Select * from spaceTable order by roomId ASC")
    fun getAllSpacesItems(): LiveData<List<Space>>

    @Update
    suspend fun update(item: ToDeclutter)

    @Update
    suspend fun update(space: Space)

    @Update
    suspend fun update(score: Score)

    @Query("Select * from  spaceTable WHERE  roomId")
    fun getAllSpaces(): LiveData<List<Space>>



}







