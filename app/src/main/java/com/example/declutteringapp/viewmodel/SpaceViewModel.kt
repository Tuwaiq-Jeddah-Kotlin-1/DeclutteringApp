package com.example.declutteringapp.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.declutteringapp.model.Space
import com.example.declutteringapp.model.ToDeclutter
import com.example.declutteringapp.model.repo.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class SpaceViewModel : ViewModel() {



    val repository: Repo = Repo()

    val allSpaces: LiveData<List<Space>> =repository.allSpaces

    fun allItems(roomId:Int): LiveData<List<ToDeclutter>> = repository.allItems(roomId)


    fun deleteSpace(space: Space) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(space)
    }




    fun deleteItem(item: ToDeclutter) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(item)
    }


    fun updateSpace(space: Space) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(space)
    }

    fun updateItem(item: ToDeclutter) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(item)
    }


    fun addSpace(space: Space) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(space)

    }


    fun addItem(item:ToDeclutter) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(item)
    }
}




