package com.example.declutteringapp

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreRepository {

    val TAG = "FIREBASE_REPOSITORY"
    var firestoreDB = FirebaseFirestore.getInstance()
    var user = FirebaseAuth.getInstance().currentUser


    // save address to firebase
    fun saveUserInfo(userInfo: UserInfoModel): Task<Void> {
        //var
        var documentReference = firestoreDB.collection("users").document(user!!.email.toString())
            .collection("saved_users").document(userInfo.userId)
        return documentReference.set(userInfo)
    }

    // get saved users from firebase

    fun getSavedUser(): CollectionReference {
        var collectionReference = firestoreDB.collection("users/${user!!.email.toString()}/saved_city")
        return collectionReference
    }

    fun deleteUser(userInfo: UserInfoModel): Task<Void> {
        var documentReference =  firestoreDB.collection("users/${user!!.email.toString()}/saved_user")
            .document(userInfo.userId)

        return documentReference.delete()
    }

}