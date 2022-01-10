package com.example.declutteringapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.declutteringapp.model.ThirtyDayRepository
import com.example.declutteringapp.model.ThirtyDays
import com.example.declutteringapp.model.ThirtyDaysDP

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class DaysViewModel  ( application: Application) : AndroidViewModel(application) {


    val allDays : LiveData<List<ThirtyDays>>
    val repository : ThirtyDayRepository



    init {
        val dao = ThirtyDaysDP.getDatabase(application).getDaysDao()
        repository = ThirtyDayRepository(dao)
        allDays = repository.allDays
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
