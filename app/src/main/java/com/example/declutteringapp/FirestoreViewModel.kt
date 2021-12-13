package com.example.declutteringapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.EventListener
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

    // save User to firebase

    fun saveUserToFirebase(userInfo: UserInfoModel){
        firebaseRepository.saveUserInfo(userInfo).addOnFailureListener {
            Log.e(TAG, "Failed to save Address!")


        }
    }
    // get realtime updates from firebase regarding saved user

    fun getUserInfo(): LiveData<List<UserInfoModel>> {
        firebaseRepository.getSavedUsers().addSnapshotListener { value, error ->

            var savedUserList : MutableList<UserInfoModel> = mutableListOf()
            for (doc in value!!) {
                var userInfo = doc.toObject(UserInfoModel::class.java)
                savedUserList.add(userInfo)
            }
            savedUser.value = savedUserList
        }

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
            //Toast.makeText(coroutineContext.javaClass, "Welcome ${user.fullName.toString()}", Toast.LENGTH_LONG).show()

        }
    } catch (e: Exception) {
        withContext(Dispatchers.Main) {
            // Toast.makeText(coroutineContext,0,0, e.message, Toast.LENGTH_LONG).show()
            Log.e("FUNCTION createUserFirestore", "${e.message}")
        }
    }
}}

