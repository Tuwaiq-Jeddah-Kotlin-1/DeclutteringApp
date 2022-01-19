package com.example.declutteringapp.model.repo

import androidx.lifecycle.LiveData
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.declutteringapp.WorkerNotification
import com.example.declutteringapp.model.Space
import com.example.declutteringapp.model.ThirtyDays
import com.example.declutteringapp.model.ToDeclutter
import com.example.declutteringapp.view.MainActivity
import java.util.concurrent.TimeUnit

class SpaceRepo(private val spaceDao: MySpacesDao,private val thirtyDaysDao: ThirtyDaysDao) {


    val allSpaces: LiveData<List<Space>> = spaceDao.getAllSpaces()

 fun allItems(roomId:Int): LiveData<List<ToDeclutter>> = spaceDao.getAllItems(roomId)


    suspend fun insert(space: Space) {
        spaceDao.insert(space)

    }



    suspend fun insert(item: ToDeclutter) {
        spaceDao.insert(item)

    }


    suspend fun update(space: Space){
        spaceDao.update(space)
    }

    suspend fun delete(space: Space){
        spaceDao.delete(space)
    }

    suspend fun delete(item: ToDeclutter){
        spaceDao.delete(item)
    }


    suspend fun update(item: ToDeclutter){
        spaceDao.update(item)
    }

    val allDays:  LiveData<List<ThirtyDays>> = thirtyDaysDao.getAllDays()


    suspend fun insert(day : ThirtyDays){
        thirtyDaysDao.insert(day)
    }

    suspend fun delete(day : ThirtyDays){
        thirtyDaysDao.delete(day)
    }


    suspend fun update(day : ThirtyDays){
        thirtyDaysDao.update(day)
    }

    class NotificationRepo {
        fun myNotification(mainActivity: MainActivity){
            val myWorkRequest= PeriodicWorkRequest
                .Builder(WorkerNotification::class.java,1, TimeUnit.DAYS)
                .setInputData(workDataOf(
                    "title" to "Do the Thirty Days Challenge\uD83D\uDCAA",
                    "message" to "Complete the challenge and feel lighter \uD83D\uDE0C")
                )
                .build()
            WorkManager.getInstance(mainActivity).enqueueUniquePeriodicWork(
                "periodicStockWorker",
                ExistingPeriodicWorkPolicy.KEEP,
                myWorkRequest
            )
        }
    }




}
