package com.example.declutteringapp.viewmodel

import androidx.lifecycle.*
import com.example.declutteringapp.model.ThirtyDays
import com.example.declutteringapp.model.repo.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class DaysViewModel  : ViewModel() {

    val repository : Repo= Repo()
    val allDays : LiveData<List<ThirtyDays>> =repository.allDays


    fun addDay(day :ThirtyDays) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(day)
    }
}
