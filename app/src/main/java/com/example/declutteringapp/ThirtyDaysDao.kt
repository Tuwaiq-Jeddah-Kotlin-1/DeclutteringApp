package com.example.declutteringapp


import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.declutteringapp.ThirtyDays

@Dao
interface ThirtyDaysDao {


        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insert(thirtyDays: ThirtyDays )


        @Delete
        suspend fun delete(thirtyDays: ThirtyDays)

        @Query("Select * from daysTable order by id ASC")
        fun getAllDays(): LiveData<List<ThirtyDays>>

        @Update
        suspend fun update(thirtyDays: ThirtyDays)

}
