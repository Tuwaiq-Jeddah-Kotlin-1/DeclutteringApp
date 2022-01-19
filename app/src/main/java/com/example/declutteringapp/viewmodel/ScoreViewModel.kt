package com.example.declutteringapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.declutteringapp.model.Score
import com.example.declutteringapp.model.SpaceDataBase
import com.example.declutteringapp.model.repo.SpaceRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ScoreViewModel(application: Application) : AndroidViewModel(application) {
  //  val allScores: LiveData<Score>
    val scores: MutableLiveData<Score> by lazy {
        MutableLiveData<Score>()
    }
    val repository: SpaceRepo

    init {
        val dao = SpaceDataBase.getDatabase(application).getSpaceDao()
        repository = SpaceRepo(dao)
    }

    fun updateScore(score: Score) = viewModelScope.launch(Dispatchers.IO) {
        repository .update(score)

    }
    fun addScore(score: Score) = viewModelScope.launch(Dispatchers.IO) {
        repository .insert(score)

    }
}