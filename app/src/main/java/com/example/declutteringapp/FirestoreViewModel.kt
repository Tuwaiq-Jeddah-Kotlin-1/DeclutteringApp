package com.example.declutteringapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot

class FirestoreViewModel : ViewModel(){

    val TAG = "FIRESTORE_VIEW_MODEL"
    var firebaseRepository = FirestoreRepository()
    var savedUser : MutableLiveData<List<UserInfoModel>> = MutableLiveData()

    // save User to firebase

    fun saveUserToFirebase(userInfoModel: UserInfoModel){
        firebaseRepository.saveUserInfo(userInfoModel).addOnFailureListener {
            Log.e(TAG,"Failed to save User!")
        }
    }

    // get realtime updates from firebase regarding saved user

    fun getUserInfo(): LiveData<List<UserInfoModel>> {
        firebaseRepository.getSavedUser().addSnapshotListener(EventListener<QuerySnapshot> { value, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                savedUser.value = null
                return@EventListener
            }

            var savedUserList : MutableList<UserInfoModel> = mutableListOf()
            for (doc in value!!) {
                var userInfo = doc.toObject(UserInfoModel::class.java)
                savedUserList.add(userInfo)
            }
            savedUser.value = savedUserList
        })

        return savedUser
    }

    // delete an address from firebase
    fun deleteCity(userInfo: UserInfoModel){
        firebaseRepository.deleteUser(userInfo).addOnFailureListener {
            Log.e(TAG,"Failed to delete User")
        }
    }

}