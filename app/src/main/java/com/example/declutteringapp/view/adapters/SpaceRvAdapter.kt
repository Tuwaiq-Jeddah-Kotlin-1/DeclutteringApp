package com.example.declutteringapp.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.declutteringapp.R
import com.example.declutteringapp.model.Space


class SpaceRvAdapter(

    val context: Context,
    val spaceClickDeleteInterface: SpaceClickDeleteInterface,
    val spaceClickInterface: SpaceClickInterface

    ) :
    RecyclerView.Adapter<SpaceRvAdapter.ViewHolder>() {


    private val allSpaces = ArrayList<Space>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.space_rv_item,
            parent, false
        )


        return ViewHolder(itemView)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var spaceTV = itemView.findViewById<TextView>(R.id.tvSpaceName)

        val statusTV = itemView.findViewById<TextView>(R.id.tvSpacestatus)
        val spaceImage: ImageView = itemView.findViewById(R.id.spaceImages)
        val deleteSpace: ImageView = itemView.findViewById(R.id.ivDeleteSpace)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var spaces = allSpaces[position]
        holder.spaceTV.setText(allSpaces.get(position).roomName)

        holder.statusTV.setText(allSpaces.get(position).status)
       allSpaces.get(position).roomId


        Glide.with(context)

            .load(spaces.imgPath)
            .into(holder.spaceImage)


        holder.deleteSpace.setOnClickListener {

            spaceClickDeleteInterface.onDeleteIconClick(allSpaces.get(position))
        }
        holder.itemView.setOnClickListener {

            spaceClickInterface.onSpaceClick(allSpaces.get(position))
            allSpaces.get(position)

        }

    }


    override fun getItemCount(): Int {

        return allSpaces.size
    }

    fun updateList(newList: List<Space>) {

        allSpaces.clear()

        allSpaces.addAll(newList)


        notifyDataSetChanged()
    }





    interface SpaceClickDeleteInterface {

        fun onDeleteIconClick(space: Space)
    }

    interface SpaceClickInterface {

        fun onSpaceClick(space:Space)
    }}




/*

class SpaceRvAdapter(): ListAdapter<SpaceItem, RecyclerView.ViewHolder>(DiffCallback()){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            TYPE_SPACE -> SpaceViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.space_rv_item,
                parent, false))
                TYPE_ITEM -> ItemViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.to_declutter_rv_item,
                parent, false))
            else -> throw IllegalArgumentException("You must supply a valid type for this adapter")
        }
    }


    internal abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(data: SpaceItem)
    }

    internal class SpaceViewHolder(itemView: View) : BaseViewHolder(itemView) {

        var spaceTV = itemView.findViewById<TextView>(R.id.tvSpaceName)
        val statusTV = itemView.findViewById<TextView>(R.id.tvSpacestatus)
        val spaceImage: ImageView = itemView.findViewById(R.id.spaceImages)
        val deleteSpace: ImageView = itemView.findViewById(R.id.ivDeleteSpace)

        override fun bind(data: SpaceItem) {

            spaceTV.text = data.space?.roomName
            statusTV.text = data.space?.status
            spaceImage.setImageResource(R.drawable.ic_image_replace)

            deleteSpace.setOnClickListener {

            }
            itemView.setOnClickListener {
*/
/*
                var items= com.example.declutteringapp.ToDeclutter("", 0)
                val  action= com.example.declutteringapp.MySpaceFragmentDirections.actionMySpaceFragment2ToShowRoomFragment(space=space, items = items )
                androidx.navigation.Navigation.findNavController(itemView).navigate(action)*//*

            }


        }
    }


  //  val item = getItem(position)

    internal class ItemViewHolder(itemView: View) : BaseViewHolder(itemView) {
        var toDeclutter = itemView.findViewById<TextView>(R.id.tvDeclutter)

        val deleteItem: ImageView = itemView.findViewById(R.id.btnDelete)

        override fun bind(data: SpaceItem) {
            toDeclutter.text = data.item?.items



            deleteItem.setOnClickListener(View.OnClickListener { v  ->})
            */
/*    val viewModel=SpaceViewModel(application = Application())
                val context = Context
                val builder = AlertDialog.Builder(context)
                builder.setMessage("Are you sure you want to Delete ${data.item?.items}?")
                    .setCancelable(false)
                    .setPositiveButton("Yes") { dialog, id ->
                        viewModel.deleteSpace(data.item.items)
                        Toast.makeText( context,"${data.item?.items} Deleted", Toast.LENGTH_LONG).show()
                    }
                    .setNegativeButton("No") { dialog, id ->
                        dialog.dismiss()
                    }
                val alert = builder.create()
                alert.show()

            })*//*

        }

        }


  */
/*
    override fun onBindViewHolder(holder: SpaceRvAdapter.ItemViewholder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: SpaceItem) = with(itemView) {
            // TODO: Bind the data with View

            setOnClickListener {
                // TODO: Handle on click
            }
        }*//*



    companion object {
        private const val TYPE_SPACE = 0
        private const val TYPE_ITEM = 1

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }


class DiffCallback : DiffUtil.ItemCallback<SpaceItem>() {


    override fun areItemsTheSame(oldItem: SpaceItem, newItem: SpaceItem): Boolean {
        return oldItem?.id == newItem?.id
    }

    override fun areContentsTheSame(oldItem: SpaceItem, newItem: SpaceItem): Boolean {
        return oldItem == newItem
    }
}}




*/




/*override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    if (viewType == VIEW_TYPE_TEXT) {
        return TextViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.text_item_layout, parent, false)
        )
    }
    return ImageViewHolder(
        LayoutInflater.from(context)
            .inflate(R.layout.image_item_layout, parent, false)
    )
}*/




























































// var itemss = allItems[position]



/*
 val layoutManager = LinearLayoutManager(
           holder.itemList
                .context,
            LinearLayoutManager.HORIZONTAL,
            false
        )

         (allSpaces.get(position).subData)

        layoutManager
            .setInitialPrefetchItemCount(
                spaces.subData.size)
*/

//instance of the adapter
/*
  var child=ToDeclutterRvAdapter(context,  DeclutterClickDeleteInterface)
     child=RecyclerView().RecycledViewPool()

     ViewHolder(ChildRecyclerView
             .setLayoutManager(layoutManager))

     parentViewHolder
         .ChildRecyclerView
         .setAdapter(childItemAdapter);
     parentViewHolder
         .ChildRecyclerView
         .setRecycledViewPool(viewPool);
 }



    val childLayoutManager = LinearLayoutManager(holder.itemView.context, RecyclerView.VERTICAL, false)

*/




/*
val adapter = ToDeclutter("")
        val linearLayoutManager = object : LinearLayoutManager(holder.itemList.context) {
            override fun canScrollVertically(): Boolean {
                return false
            }*/
/*  holder.itemList.apply {
                     linearLayoutManager
                    this.adapter = adapter
                    addItemDecoration(ToDeclutter(this.context, RecyclerView.VERTICAL))

 override  fun toDeclutter(itemId: Int, roomName: String, position: Int)
    {
        commentid = replyid
        replyCommentPosition = position
        listener.postCommentAsReply(username)
    }
}}*/