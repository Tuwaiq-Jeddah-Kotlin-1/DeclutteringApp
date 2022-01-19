package com.example.declutteringapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.declutteringapp.model.repo.FirestoreRepository
import com.example.declutteringapp.model.UserInfoModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FirestoreViewModel : ViewModel(){

    val TAG = "FIRESTORE_VIEW_MODEL"
    var firebaseRepository = FirestoreRepository()
    var savedUser : MutableLiveData<List<UserInfoModel>> = MutableLiveData()


    fun saveUserToFirebase(userInfo: UserInfoModel){
        firebaseRepository.saveUserInfo(userInfo).addOnFailureListener {
            Log.e(TAG, "Failed to save Address!")


        }
    }

    fun getUserInfo(): LiveData<List<UserInfoModel>> {

        firebaseRepository.getSavedUsers().addSnapshotListener { value: QuerySnapshot?, error ->

            var savedUserList : MutableList<UserInfoModel> = mutableListOf()
            if (value != null) {
                for (doc in value) {

                    var userInfo = doc.toObject(UserInfoModel::class.java)
                    savedUserList.add(userInfo)
                }
                savedUser.value = savedUserList
            }}
        return savedUser
    }


    fun deleteUser(userInfo: UserInfoModel) {
        firebaseRepository.deleteUser(userInfo).addOnFailureListener {
            Log.e(TAG, "Failed to delete User")
        }}



 fun insertUser(email: String, name: String, password: String) {

    val userInfo = UserInfoModel()

    userInfo.email = email
    userInfo.name = name
    userInfo.password=password


    createUserFirestore(userInfo)
}

    fun updateFirestoreEmail(userInfo: UserInfoModel) = viewModelScope.launch(Dispatchers.IO) {
        firebaseRepository.updateUserEmail(userInfo = UserInfoModel())
    }


 fun createUserFirestore(userInfo: UserInfoModel) = CoroutineScope(Dispatchers.IO).launch {

     try {
         val userRef = Firebase.firestore.collection("users")

         val uId = FirebaseAuth.getInstance().currentUser?.uid

         userRef.document("$uId").set(userInfo).addOnCompleteListener {
             it
             when {
                 it.isSuccessful -> {
                     saveUserToFirebase(userInfo)
                 }
                 else -> {
                 }
             }
         }
         withContext(Dispatchers.Main) {

         }
     } catch (e: Exception) {
         withContext(Dispatchers.Main) {
             Log.e("FUNCTION createUserFirestore", "${e.message}")
         }
     }
 }
     fun readUserData() = CoroutineScope(Dispatchers.IO).launch {

         val uId = FirebaseAuth.getInstance().currentUser!!.uid
         try {
             //coroutine
             val db = FirebaseFirestore.getInstance()
             db.collection("users").document("$uId")
                 .get().addOnCompleteListener {
                     it
                     if (it.getResult()?.exists()!!) {
                         var name = it.getResult()!!.getString("name")

                         Log.e("error \n", "name $name")
                     } else {
                         Log.e("error \n", "errooooooorr")
                     }

                 }

         } catch (e: Exception) {
             withContext(Dispatchers.Main) {
                 Log.e("FUNCTION createUserFirestore", "${e.message}")
             }
         }

     }

 }

