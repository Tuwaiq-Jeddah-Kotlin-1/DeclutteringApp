package com.example.declutteringapp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.declutteringapp.R
import com.example.declutteringapp.model.Space
import com.example.declutteringapp.model.ToDeclutter


class ToDeclutterAdapter (
        val context: Context,
        val clickDeleteInterface: ClickDeleteInterface
        ) :
        RecyclerView.Adapter<ToDeclutterAdapter.ViewHolder>() {



        private val allItems = Space("","","", subData =   ArrayList<ToDeclutter>())


    var itemss=allItems.subData


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.to_declutter_rv_item,
                parent, false
            )


            return ViewHolder(itemView)
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            var tvItem = itemView.findViewById<TextView>(R.id.tvDeclutter)
            var ivDelete=itemView.findViewById<ImageView>(R.id.btnDelete)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            var items = itemss[position]
            holder.tvItem.setText(items.items)
          //  allItems.get(position).roomId

            holder.ivDelete.setOnClickListener {

               clickDeleteInterface.onDeleteIconClick(itemss.get(position))
            }
      }


        override fun getItemCount(): Int {

            return itemss.size
        }

        fun updateList(newList: List<ToDeclutter>) {

            itemss.clear()

            itemss.addAll(newList)


            notifyDataSetChanged()
        }



        interface ClickDeleteInterface {

            fun onDeleteIconClick(items: ToDeclutter)
        }

 }












