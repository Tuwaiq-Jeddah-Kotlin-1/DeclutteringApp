package com.example.declutteringapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.declutteringapp.model.repo.ThirtyDayRepository
import com.example.declutteringapp.model.ThirtyDays
import com.example.declutteringapp.model.ThirtyDaysDP

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class DaysViewModel  ( application: Application) : AndroidViewModel(application) {
    val daysData= mutableListOf<ThirtyDays>()

  // var _da

    val allDays : LiveData<List<ThirtyDays>>
    //allDays.value=daysData

    val repository : ThirtyDayRepository

    fun allDaysITem(dayId:Int): LiveData<List<ThirtyDays>> = repository.allItems(dayId)

    init {
        val dao = ThirtyDaysDP.getDatabase(application).getDaysDao()
        repository = ThirtyDayRepository(dao)
        allDays = repository.allDays
        for (i in 1..30) {
            daysData.add(ThirtyDays(i , 0, ""))
        }
     //  allDays.value=daysData
    }


    fun deleteDay (day :ThirtyDays) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(day)
    }


    fun updateDay(day :ThirtyDays) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(day)
    }

    fun addDay(day :ThirtyDays) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(day)
    }
}
