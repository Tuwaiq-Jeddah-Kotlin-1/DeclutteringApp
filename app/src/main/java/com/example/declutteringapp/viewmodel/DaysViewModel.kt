package com.example.declutteringapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.declutteringapp.model.SpaceDataBase
import com.example.declutteringapp.model.ThirtyDays
import com.example.declutteringapp.model.SpaceRepo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class DaysViewModel  : ViewModel() {

    val repository : SpaceRepo=SpaceRepo()
    val allDays : LiveData<List<ThirtyDays>> =repository.allDays



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
