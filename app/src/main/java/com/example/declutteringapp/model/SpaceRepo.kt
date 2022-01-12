package com.example.declutteringapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SpaceRepo(private val spaceDao: MySpacesDao) {


    val allSpaces: LiveData<List<Space>> = spaceDao.getAllSpaces()
    val allScores: LiveData<List<Score>> = spaceDao.getAcores()
 fun allItems(roomId:Int): LiveData<List<ToDeclutter>> = spaceDao.getAllItems(roomId)
  // val getSpaceAndItem: LiveData<List<SpaceItemRelation>> = spaceDao.getAllItemss(roomId = 0)


    suspend fun insert(space: Space) {
        spaceDao.insert(space)

    }
    suspend fun insert(score: Score) {
        spaceDao.insert(score)

    }


    suspend fun update(score: Score){
        spaceDao.update(score)
    }


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
