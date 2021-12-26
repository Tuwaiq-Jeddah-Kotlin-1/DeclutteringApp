package com.example.declutteringapp

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SpaceSharedViewModel: ViewModel(){
        private val _itemNum =  MutableLiveData<Int>(0)
        val itemNum: LiveData<Int> = _itemNum

        private val _roomName =  MutableLiveData<String>("")
        val roomName: LiveData<String> = _roomName

    private val _roomStat =  MutableLiveData<String>("")
    val roomStat: LiveData<String> = _roomStat

        private val _roomImage =  MutableLiveData<Uri>()
        val roomImage : LiveData<Uri> = _roomImage

    fun setItemNumbers(ItemNumbers: Int) {
        _itemNum.value = ItemNumbers
    }

    fun setRoomStats(RoomsStats: String) {
        _roomStat.value = RoomsStats
    }

    fun setRoomName(roomNames: String) {
        _roomName.value = roomNames
    }

    fun setSpaceImage(spaceImages: Uri) {
        _roomImage.value = spaceImages
    }


    fun clutteredOrNot(): Boolean {
        return _roomStat.value.isNullOrEmpty()
    }


}