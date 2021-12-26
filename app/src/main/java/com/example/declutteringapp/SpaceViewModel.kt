package com.example.declutteringapp
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
class SpaceViewModel  (application: Application) :AndroidViewModel(application) {

        // on below line we are creating a variable
        // for our all notes list and repository
        val allSpaces : LiveData<List<Space>>
        val repository : SpaceRepo


        init {
            val dao = SpaceDataBase.getDatabase(application).getSpaceDao()
            repository = SpaceRepo(dao)
            allSpaces = repository.allSpaces
        }


        fun deleteSpace (space:Space) = viewModelScope.launch(Dispatchers.IO) {
            repository.delete(space)
        }


        fun updateSpace(space:Space)= viewModelScope.launch(Dispatchers.IO) {
            repository.update(space)
        }



        fun addSpace(space:Space) = viewModelScope.launch(Dispatchers.IO) {
            repository.insert(space)
        }
    }



