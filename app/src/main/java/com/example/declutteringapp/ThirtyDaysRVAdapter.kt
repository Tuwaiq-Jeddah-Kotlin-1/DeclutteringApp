package com.example.declutteringapp
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import com.bumptech.glide.Glide


/*
class ThirtyDaysRVAdapter(
    private val allDays: List<ThirtyDays>,
    private val listener: Communicator
) :
    RecyclerView.Adapter<ThirtyDaysRVAdapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = allDays[position]
        Glide.with(holder.itemView.context)
            .load(currentItem.imgPath)
            .into(holder.imageView)
        holder.itemCount.text = currentItem.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.thirty_day_rv_item, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount() = allDays.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val itemCount = itemView.findViewById<TextView>(R.id.rvThirtyText)
        val itemNum = itemView.findViewById<TextView>(R.id.rvItemNum)
        val imageView: ImageView = itemView.findViewById(R.id.rvThirtyImg)


        init {
            itemView.setOnClickListener (this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            val image = allDays[adapterPosition].imgPath
            val count =  allDays[adapterPosition].itemCounts
            if (position != RecyclerView.NO_POSITION) {
                if (image != null) {
                    listener.passData(position,count, image)
                }
            }
        }
    }
}
*/


class ThirtyDaysRVAdapter(
    val context: Context,
    val dayClickInterface: DayClickInterface,
) :
    RecyclerView.Adapter<ThirtyDaysRVAdapter.ViewHolder>() {

    var daysLimit= 30


    // on below line we are creating a
    // variable for our all dyas list.
    private var allDays = ArrayList<ThirtyDays>()

    // on below line we are creating a view holder class.
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // on below line we are creating an initializing all our
        // variables which we have added in layout file.
        val itemCount = itemView.findViewById<TextView>(R.id.rvItemNum)
        val itemNum = itemView.findViewById<TextView>(R.id.rvItemNum)
        val imageView: ImageView = itemView.findViewById(R.id.rvThirtyImg)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflating our layout file for each item of recycler view.
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.thirty_day_rv_item,
            parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // on below line we are setting data to item of recycler view.
        holder.itemCount.setText(allDays.get(position).itemCounts.toString())
        holder.itemNum.setText(allDays.get(position).dayNum.toString())
  //holder.imageView.setImage(allDays.get(position).imgPath!!)


allDays[position].imgPath
            holder.imageView.setImageBitmap(BitmapFactory.decodeFile(allDays[position].imgPath.toString()))


        holder.itemView.setOnClickListener {

            dayClickInterface.onDayClick(allDays.get(position))
        }
    }


    override fun getItemCount(): Int {
return allDays.size

            }








    fun updateList(newList: List<ThirtyDays>) {

        allDays.clear()

        allDays.addAll(newList)

        notifyDataSetChanged()
    }



interface DayClickInterface {

    fun onDayClick(thirtyDays: ThirtyDays)
}


    fun setData(arrDaysList: List<ThirtyDays>) {
        var arrlist = arrDaysList as ArrayList<ThirtyDays>
        arrlist +30
    }

    }

