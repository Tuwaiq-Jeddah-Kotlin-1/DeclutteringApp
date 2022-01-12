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
  @ColumnInfo (name = "itemsList")var subData: ArrayList<ToDeclutter>,
                    //  @Ignore  @Embedded val items: ToDeclutter,

                     @PrimaryKey(autoGenerate = true) var roomId: Int = 0): Parcelable



@Parcelize
@Entity(tableName = "declutterTable",
    indices = [Index(value = ["items"],unique = true), Index(value = ["itemId"], unique = true)] /*Index(value = ["roomId"], unique = true)]*/)

class ToDeclutter(@ColumnInfo(name = "items")val items: String,
                @ColumnInfo(name = "roomId")val roomId: Int,
          /*        @ColumnInfo(name = "roomImage")val roomImages: String*/

                  @PrimaryKey(autoGenerate = true) var itemId: Int = 0 ): Parcelable


//@Entity(
  /*  tableName = "EveryThings",
    primaryKeys = [
        "space_id", "item_id"
    ],
    foreignKeys = [
        ForeignKey(entity = Space::class, parentColumns = ["roomId"], childColumns = ["space_id"]),
        ForeignKey(entity = ToDeclutter::class, parentColumns = ["itemId"], childColumns = ["item_id"])
    ],*/
 /*   indices = [
       // Index("id"),
        Index("space_id"),
        Index("item_id")])
data class SpaceItemBoth (*/
   // @ColumnInfo(name = "id")  val id: Int = 0,
    /*@ColumnInfo(name = "space_id") val roomId: Int,
    @ColumnInfo(name = "item_id") val itemId: Int,*/
   // @ColumnInfo(name = "quantity") val quantity: Double /** Quantity of the item in the referenced location */
//)

/*

class SpaceItemRelation {
    @Embedded
    lateinit var space: Space
    @Relation(
        parentColumn = "roomId",
        entityColumn = "itemId",
        associateBy = Junction(value = SpaceItemBoth::class, parentColumn = "space_id", entityColumn = "item_id")
    ) lateinit var items: List<ToDeclutter>}
*/


//@Entity(tableName = "SpaceAndItemTable")
/*

data class SpaceAndItem(

    @Embedded val space: Space,
    @Relation(
        parentColumn = "roomId",
        entityColumn = "itemId"
    )
    val item: ToDeclutter
)
*/

/*

@Entity(
    primaryKeys = ["spacesID", "decluttringID"]
)
data class SpacesItems(
    val spacesID: Int=0,
    val decluttringId: Int=0
)
data class SpacesWithItems (
    @Embedded
    val space: Space,
    @Relation(
        parentColumn = "roomId",
        entity = Space::class,
        entityColumn = "itemId",
        associateBy = Junction(
            value = SpacesItems::class,
            parentColumn = "spacesID",
            entityColumn = "decluttringID"
        )
    )
    val items: List<ToDeclutter>
)

data class ItemsWithSpaces (
    @Embedded
    val items: ToDeclutter,
    @Relation(
        parentColumn = "itemId",
        entity = ToDeclutter::class,
        entityColumn = "roomId",
        associateBy = Junction(
            value = SpacesItems::class,
            parentColumn = "decluttringID",
            entityColumn = "spacesID"
        )
    )
    val space: List<Space>
)

*/

/*

@Entity(tableName = "parentChild")

data class SpaceItem(
    @ColumnInfo(name = "space") var space: Space,
    @ColumnInfo(name = "item") var item: ToDeclutter, @PrimaryKey(autoGenerate = true) var id: Int = 0)
*/
