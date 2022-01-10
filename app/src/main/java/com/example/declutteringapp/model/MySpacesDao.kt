package com.example.declutteringapp.model

import androidx.lifecycle.LiveData
import androidx.room.*

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
    suspend fun insert(item:ToDeclutter)

  /*  @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(spaceItem: SpaceItemBoth)
*/

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


    @Query("Select * from declutterTable order by itemId ASC")
    fun getAllItems(): LiveData<List<ToDeclutter>>



/*

    @Query("SELECT * FROM declutterTable WHERE roomId")
    fun getRoomAndItem(): LiveData<List<ToDeclutter>>
*/
/*
    @Query("SELECT * FROM spaceTable WHERE roomId")
    fun getSpaceID(): LiveData<List<Space>>

    fun insertitemForSpace(space:Space, items:List<ToDeclutter>){

        for(items in items ){
            space.roomId
        }

      //  insert(items)
    }*/
    @Update
    suspend fun update(item: ToDeclutter)

    @Update
    suspend fun update(space: Space)

    @Query("Select * from  spaceTable WHERE  roomId")
    fun getAllSpaces(): LiveData<List<Space>>



}







