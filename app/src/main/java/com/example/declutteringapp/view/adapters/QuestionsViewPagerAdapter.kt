package com.example.declutteringapp.view.adapters

import android.app.AlertDialog
import com.example.declutteringapp.R
import com.example.declutteringapp.databinding.QuestionsLayoutBinding


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.declutteringapp.model.KeepOrTossModel

class QuestionsViewPagerAdapter( val context: Context,private var listQuestions: MutableList<KeepOrTossModel>?, val resultDialog: ResultDialog) :
    RecyclerView.Adapter<QuestionsViewPagerAdapter.MyViewHolder>() {

    class MyViewHolder(val itemBinding: QuestionsLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root){

        private var binding : QuestionsLayoutBinding? = null

        init {
            this.binding = itemBinding
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: QuestionsLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.questions_layout,parent,false)
        return MyViewHolder(binding)
       // var vpText=
    }

    override fun getItemCount(): Int {
        listQuestions?.let {
            return it.size
        }
        return 0

    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemBinding.ques = listQuestions?.get(position)
        if(position == itemCount - 1){
    /*      val builder = AlertDialog.Builder(context)
            builder.setTitle("You're Done")
            builder.setMessage("Hopefully you can see to witch side the item is closer ")
            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                Toast.makeText(context,
                    android.R.string.yes, Toast.LENGTH_SHORT).show()
            }
            builder.show()*/
            resultDialog.dilog()
    }


//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))
               // builder.d()

}

interface ResultDialog {
    fun dilog() {
    }
}
    }






