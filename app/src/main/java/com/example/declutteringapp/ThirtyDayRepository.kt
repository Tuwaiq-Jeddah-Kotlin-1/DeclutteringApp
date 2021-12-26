package com.example.declutteringapp


import androidx.lifecycle.LiveData

class ThirtyDayRepository(private val thirtyDaysDao: ThirtyDaysDao) {

    val allDays:  LiveData<List<ThirtyDays>> = thirtyDaysDao.getAllDays()

    suspend fun insert(day :ThirtyDays){
        thirtyDaysDao.insert(day)
    }

    suspend fun delete(day :ThirtyDays){
        thirtyDaysDao.delete(day)
    }


    suspend fun update(day :ThirtyDays){
        thirtyDaysDao.update(day)
    }
}
