package com.example.declutteringapp.model

import androidx.lifecycle.LiveData

class SpaceRepo(private val spaceDao: MySpacesDao) {


    val allSpaces: LiveData<List<Space>> = spaceDao.getAllSpaces()
 val allItems: LiveData<List<ToDeclutter>> = spaceDao.getAllItems()
  // val getSpaceAndItem: LiveData<List<SpaceItemRelation>> = spaceDao.getAllItemss(roomId = 0)




 /*   suspend fun insert(spaceItemRelation: SpaceItemRelation) {
       spaceDao.insert(spaceItemRelation)

    }*/
    suspend fun insert(space: Space) {
        spaceDao.insert(space)

    }

/*
    suspend fun delete(itemSpace: SpaceAndItem){
        spaceDao.delete(itemSpace)
    }

    suspend fun update(itemSpace: SpaceAndItem){
        spaceDao.update(itemSpace)
    }
*/


/*
 suspend fun insert(both: SpaceAndItem) {
        spaceDao.insert(both)

    }*/
    suspend fun insert(item:ToDeclutter) {
        spaceDao.insert(item)

    }


    suspend fun update(space: Space){
        spaceDao.update(space)
    }

    suspend fun delete(space: Space){
        spaceDao.delete(space)
    }

    suspend fun delete(item:ToDeclutter){
        spaceDao.delete(item)
    }


    suspend fun update(item:ToDeclutter){
        spaceDao.update(item)
    }

}
