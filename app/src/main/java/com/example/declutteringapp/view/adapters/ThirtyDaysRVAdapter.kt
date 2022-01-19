package com.example.declutteringapp.view.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import com.bumptech.glide.Glide
import com.example.declutteringapp.R
import com.example.declutteringapp.model.ThirtyDays


class ThirtyDaysRVAdapter(
    val context: Context,
    val dayClickInterface: DayClickInterface,
    val allDays: ArrayList<ThirtyDays>

) :
    RecyclerView.Adapter<ThirtyDaysRVAdapter.ViewHolder>() {

    var daysLimit= 30
  //  val allDays= ArrayList<ThirtyDays>()


    //   private var allDays = ArrayList<ThirtyDays>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val itemCount = itemView.findViewById<TextView>(R.id.rvItemNum)
        val itemNum = itemView.findViewById<TextView>(R.id.rvItemNum)
        val imageView: ImageView = itemView.findViewById(R.id.rvThirtyImg)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.thirty_day_rv_item,
            parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var days = allDays[position]

        holder.itemCount.setText(allDays.get(position).itemCounts.toString())

        holder.itemNum.setText((position+1).toString())


        //holder.imageView.setImage(allDays.get(position).imgPath!!)


        Glide.with(context)
            .load(days.imgPath)
            .into(holder.imageView)
        /*allDays[position].imgPath
                   holder.imageView.setImageBitmap(BitmapFactory.decodeFile(allDays[position].imgPath.toString()))
       */


        holder.itemView.setOnClickListener {

            dayClickInterface.onDayClick(allDays.get(position))
            allDays.get(position)
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

/*

class ThirtyDaysRVAdapter(
    val context: Context,
    val dayClickInterface: DayClickInterface,
) :
    RecyclerView.Adapter<ThirtyDaysRVAdapter.DaysViewHolder>(){


    private var allDays = ArrayList<ThirtyDays>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.thirty_day_rv_item,
            parent, false
        )
        return DaysViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DaysViewHolder, position: Int) {
     //   var da = allDays[position.absoluteValue]
       // holder.itemNum.text[position].values
     //   val modelObject = allDays.values[position]

             //   holder.itemNum.setText(da.dayNum.toString())
        val dayss = differ.currentList[position]
        holder.bind(dayss)

    }

    inner class DaysViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val itemCount = itemView.findViewById<TextView>(R.id.rvThirtyText)
        val itemNum = itemView.findViewById<TextView>(R.id.rvItemNum)
        val imageView: ImageView = itemView.findViewById(R.id.rvThirtyImg)



        fun bind(dayss: ThirtyDays?) {
            //check for null
            dayss?.let {
                    itemCount.setText(dayss.itemCounts.toString())
                itemNum.setText(dayss.dayNum.toString())

                Glide.with(context)
                    .load(dayss.imgPath)
                    .into(imageView)
                itemView.setOnClickListener {


                    dayClickInterface.onDayClick(allDays.get(position))

                }


            }
        }
    }




    override fun getItemCount(): Int {
return  differ.currentList.size

            }

*/


/*

    fun updateList(newList: List<ThirtyDays>) {

       allDays.clear()

        allDays.addAll(newList)

        notifyDataSetChanged()
    }
*/

/*


interface DayClickInterface {

    fun onDayClick(thirtyDays: ThirtyDays)
}


    private val differCallback = object : DiffUtil.ItemCallback<ThirtyDays>() {
        override fun areItemsTheSame(oldItem: ThirtyDays, newItem: ThirtyDays): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: ThirtyDays, newItem: ThirtyDays): Boolean {
            return oldItem == newItem

        }
    }
    val differ = AsyncListDiffer(this, differCallback)



*/



//holder.imageView.setImage(allDays.get(position).imgPath!!)




//holder.imageView.setImageBitmap(BitmapFactory.decodeFile(allDays[position].imgPath))



