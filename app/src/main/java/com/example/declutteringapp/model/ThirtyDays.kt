package com.example.declutteringapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "daysTable")
class ThirtyDays (@ColumnInfo(name = "dayNum")val dayNum :Int?, @ColumnInfo(name = "img_path") var imgPath: String?,


  @PrimaryKey(autoGenerate = true)
  var id: Int = 0 ):Parcelable

