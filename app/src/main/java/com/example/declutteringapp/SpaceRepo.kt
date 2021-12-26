package com.example.declutteringapp

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import java.util.concurrent.Flow

class SpaceRepo(private val spaceDao: MySpacesDao) {


    val allSpaces: LiveData<List<Space>> = spaceDao.getAllSpaces()


    suspend fun insert(space: Space) {
        spaceDao.insert(space)
    }


    suspend fun delete(space: Space){
        spaceDao.delete(space)
    }


    suspend fun update(space: Space){
        spaceDao.update(space)
    }
}
