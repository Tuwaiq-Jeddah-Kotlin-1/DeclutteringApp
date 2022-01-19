package com.example.declutteringapp.model
import android.net.Uri
import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

@Parcelize
    @Entity(tableName = "spaceTable",
        indices = [Index(value = ["roomId"], unique = true), Index(value = ["roomName"], unique = true), Index(value = ["status"], unique = false)])

    data class Space(@ColumnInfo(name = "roomName") var roomName:String,
                     @ColumnInfo(name = "status") var status:String,
                     @ColumnInfo(name = "img_path") var imgPath: String?,
                     @PrimaryKey(autoGenerate = true) var roomId: Int = 0): Parcelable



@Parcelize
@Entity(tableName = "declutterTable",
    indices = [Index(value = ["items"],unique = true), Index(value = ["itemId"], unique = true)] /*Index(value = ["roomId"], unique = true)]*/)

class ToDeclutter(@ColumnInfo(name = "items")val items: String,
                @ColumnInfo(name = "roomId")val roomId: Int,

                  @PrimaryKey(autoGenerate = true) var itemId: Int = 0 ): Parcelable

