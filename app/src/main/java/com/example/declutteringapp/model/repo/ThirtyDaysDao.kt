package com.example.declutteringapp.model.repo


import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.declutteringapp.model.ThirtyDays

@Dao
interface ThirtyDaysDao {


        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insert(thirtyDays: ThirtyDays)


        @Query("Select * from daysTable WHERE  id = :id")
        fun getAllItems(id: Int): LiveData<List<ThirtyDays>>


        @Delete
        suspend fun delete(thirtyDays: ThirtyDays)

        @Query("Select * from daysTable order by id ASC")
        fun getAllDays(): LiveData<List<ThirtyDays>>

        @Update
        suspend fun update(thirtyDays: ThirtyDays)

}
