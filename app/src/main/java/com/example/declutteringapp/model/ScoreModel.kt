package com.example.declutteringapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "scoreTable",   indices = [Index(value = ["id"], unique = true), Index(value = ["score"], unique = true)])


data class Score(@ColumnInfo(name = "score") var score:Int=0,
                 @PrimaryKey(autoGenerate = true) var id: Int = 0): Parcelable