package com.example.declutteringapp

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
class FirestoreRepository {

    val TAG = "FIREBASE_REPOSITORY"
    var firestoreDB = FirebaseFirestore.getInstance()
    var user = FirebaseAuth.getInstance().currentUser


    // save address to firebase
    fun saveUserInfo(userInfo: UserInfoModel):Task<Void>{
        //var
        var documentReference = Firebase.firestore.collection("users")
            .document("uid")
return documentReference.set(userInfo)

    }

    // get saved addresses from firebase
    fun getSavedUsers(): CollectionReference {
        var collectionReference = Firebase.firestore.collection("users")
            .document("name").collection("userRecord")

        return collectionReference
    }

    fun deleteUser(userInfo: UserInfoModel): Task<Void> {
        var documentReference = Firebase.firestore.collection("users")
            .document("name").collection("userRecord").document(userInfo.name)

        return documentReference.delete()
    }

}