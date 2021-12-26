package com.example.declutteringapp

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
    @Entity(tableName = "spaceTable")

    class Space (@ColumnInfo(name = "roomName")val roomName :String,@ColumnInfo(name = "status")val status :String,  @ColumnInfo(name = "img_path")
var imgPath: String?= null):Parcelable {

        @PrimaryKey(autoGenerate = true) var id = 0
}