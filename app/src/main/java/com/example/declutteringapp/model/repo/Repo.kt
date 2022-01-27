package com.example.declutteringapp.model.repo

import androidx.lifecycle.LiveData
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.declutteringapp.GlobalApplication
import com.example.declutteringapp.WorkerNotification
import com.example.declutteringapp.model.*
import com.example.declutteringapp.view.MainActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

class Repo {

    val TAG = "FIREBASE_REPOSITORY"
    var firestoreDB = FirebaseFirestore.getInstance()
    var user = FirebaseAuth.getInstance().currentUser


    fun saveUserInfo(userInfo: UserInfoModel):Task<Void>{
        //var
        var documentReference = Firebase.firestore.collection("users")
            .document("uid")
return documentReference.set(userInfo)

    }

    // get saved users from firebase
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

    fun updateUserName(userInfo: UserInfoModel): Task<Void> {
     //   firestoreDB.collection("users").document(id.toString()).update("name",username.text.toString())

        var documentReference = Firebase.firestore.collection("users")
            .document("name").collection("userRecord").document(userInfo.name)

        return documentReference.update("name",user)


    }
    fun updateUserEmail(userInfo: UserInfoModel): Task<Void> {
        var documentReference = Firebase.firestore.collection("users")
            .document("email").collection("userRecord").document(userInfo.email)

        return documentReference.update("email",user)
    }


    val spaceDao= SpaceDataBase.getDatabase(GlobalApplication.getAppContext()).getSpaceDao()

    val allSpaces: LiveData<List<Space>> = spaceDao.getAllSpaces()

    fun allItems(roomId:Int): LiveData<List<ToDeclutter>> = spaceDao.getAllItems(roomId)

    val allDays: LiveData<List<ThirtyDays>> = spaceDao.getAllDays()

    suspend fun insert(space: Space) {
        spaceDao.insert(space)

    }

    suspend fun insert(item: ToDeclutter) {
        spaceDao.insert(item)

    }


    suspend fun update(space: Space){
        spaceDao.update(space)
    }

    suspend fun delete(space: Space){
        spaceDao.delete(space)
    }

    suspend fun delete(item: ToDeclutter){
        spaceDao.delete(item)
    }


    suspend fun update(item: ToDeclutter){
        spaceDao.update(item)
    }



    suspend fun insert(day : ThirtyDays){
        spaceDao.insert(day)
    }

    suspend fun delete(day : ThirtyDays){
        spaceDao.delete(day)
    }


    suspend fun update(day : ThirtyDays){
        spaceDao.update(day)
    }

    class NotificationRepo {
        fun myNotification(mainActivity: MainActivity){
            val myWorkRequest= PeriodicWorkRequest
                .Builder(WorkerNotification::class.java,1, TimeUnit.DAYS)
                .setInputData(
                    workDataOf(
                    "title" to "Do the Thirty Days Challenge\uD83D\uDCAA",
                    "message" to "Complete the challenge and feel lighter \uD83D\uDE0C")
                )
                .build()
            WorkManager.getInstance(mainActivity).enqueueUniquePeriodicWork(
                "periodicStockWorker",
                ExistingPeriodicWorkPolicy.KEEP,
                myWorkRequest
            )
        }
    }




}