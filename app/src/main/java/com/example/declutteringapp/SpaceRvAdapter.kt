package com.example.declutteringapp

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import android.os.Environment
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class SpaceRvAdapter (
        val context: Context,
        val spaceClickDeleteInterface: SpaceClickDeleteInterface,
        val spaceClickInterface: SpaceClickInterface
    ) :
        RecyclerView.Adapter<SpaceRvAdapter.ViewHolder>() {

        private val allSpaces = ArrayList<Space>()

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val spaceTV = itemView.findViewById<TextView>(R.id.tvSpaceName)
            val statusTV = itemView.findViewById<TextView>(R.id.tvSpacestatus)
            val spaceImage: ImageView = itemView.findViewById(R.id.spaceImages)
            val deleteSpace: ImageView = itemView.findViewById(R.id.ivDeleteSpace)

        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.space_rv_item,
                parent, false
            )
            return ViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            var spaces = allSpaces[position]
            holder.spaceTV.setText(allSpaces.get(position).roomName)
            holder.statusTV.setText( allSpaces.get(position).status)

       // val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())


          //  val file =context.getExternalFilesDir(null)?.absolutePath + File.separator.toString() + "xxx/xxx/" + "JPEG_" + timeStamp + "_"

      holder.spaceImage.setOnClickListener{
          spaceClickInterface.onSpaceClick(allSpaces.get(position))
      }
            holder.deleteSpace.setOnClickListener {

                spaceClickDeleteInterface.onDeleteIconClick(allSpaces.get(position))
            }

            holder.itemView.setOnClickListener {

                spaceClickInterface.onSpaceClick(allSpaces.get(position))
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
    }

    interface SpaceClickDeleteInterface {

        fun onDeleteIconClick(space: Space)
    }

    interface SpaceClickInterface {

        fun onSpaceClick(space: Space)
    }

