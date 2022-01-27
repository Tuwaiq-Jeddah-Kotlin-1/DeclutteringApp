package com.example.declutteringapp.view.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.declutteringapp.R
import com.example.declutteringapp.model.ThirtyDays


class ThirtyDaysRVAdapter(
    val context: Context,

) :
    RecyclerView.Adapter<ThirtyDaysRVAdapter.ViewHolder>() {

    val allDays= ArrayList<ThirtyDays>()
var limit =30

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

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



        holder.itemNum.setText((position+1).toString())

        Glide.with(context)
            .load(days.imgPath)
            .into(holder.imageView)

        holder.itemView.setOnClickListener {

            allDays.get(position)
        }



    }


    override fun getItemCount(): Int {
        if(allDays.size>=limit){
            Toast.makeText(context, "You have finshed the challenge", Toast.LENGTH_SHORT).show()
        return limit}

        else {return allDays.size}

    }




    fun updateList(newList: List<ThirtyDays>) {

        allDays.clear()

        allDays.addAll(newList)

        notifyDataSetChanged()
    }




}

