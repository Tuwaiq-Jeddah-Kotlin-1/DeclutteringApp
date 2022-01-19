package com.example.declutteringapp.viewmodel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.declutteringapp.model.Space
import com.example.declutteringapp.model.SpaceDataBase
import com.example.declutteringapp.model.repo.SpaceRepo
import com.example.declutteringapp.model.ToDeclutter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class SpaceViewModel  (application: Application) :AndroidViewModel(application) {


    val allSpaces: LiveData<List<Space>>

  //  val getSpaceAndItem: LiveData<List<SpaceItemRelation>>
//val getAllItemsT :LiveData<List<ToDeclutter>>
    // val getSpaceAndItem: LiveData<List<Space>>*/

    val repository: SpaceRepo
    fun allItems(roomId:Int): LiveData<List<ToDeclutter>> = repository.allItems(roomId)

    init {
        val roomDao = SpaceDataBase.getDatabase(application).getSpaceDao()
        val daysDao = SpaceDataBase.getDatabase(application).getDaysDao()

        repository = SpaceRepo(roomDao, daysDao )
        allSpaces = repository.allSpaces
            //  getSpaceAndItem = repository.getSpaceAndItem
       // getAllItemsT=repository.getAllItems
        //  getSpaceAndItem=repository.getSpaceAndItem*/
    }


    fun deleteSpace(space: Space) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(space)
    }


   /* fun deleteItem(spaceItemRelation: SpaceItemRelation) = viewModelScope.launch(Dispatchers.IO) {}
    repository.delete(spaceItemRelation)}*/

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
/*

    fun addBoth(spaceItemRelation: SpaceItemRelation) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(spaceItemRelation)

    }
*/

    fun addItem(item:ToDeclutter) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(item)
    }
}
        /* fun addBoth(both:SpaceAndItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(both)*/






