package com.example.declutteringapp.model.repo


import androidx.lifecycle.LiveData
import com.example.declutteringapp.model.ThirtyDays

class ThirtyDayRepository(private val thirtyDaysDao: ThirtyDaysDao) {

    val allDays:  LiveData<List<ThirtyDays>> = thirtyDaysDao.getAllDays()

    fun allItems(id:Int): LiveData<List<ThirtyDays>> = thirtyDaysDao.getAllItems(id)

    suspend fun insert(day : ThirtyDays){
        thirtyDaysDao.insert(day)
    }

    suspend fun delete(day : ThirtyDays){
        thirtyDaysDao.delete(day)
    }


    suspend fun update(day : ThirtyDays){
        thirtyDaysDao.update(day)
    }
}
