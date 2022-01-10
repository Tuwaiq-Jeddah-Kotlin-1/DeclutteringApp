package com.example.declutteringapp.view

import com.example.declutteringapp.R
import com.example.declutteringapp.databinding.QuestionsLayoutBinding


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.declutteringapp.model.KeepOrTossModel

class QuestionsViewPagerAdapter(private var ctx: Context?, private var listQuestions: MutableList<KeepOrTossModel>?) :
    RecyclerView.Adapter<QuestionsViewPagerAdapter.MyViewHolder>() {

    class MyViewHolder(val itemBinding: QuestionsLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root){

        private var binding : QuestionsLayoutBinding? = null

        init {
            this.binding = itemBinding
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(ctx)
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
    }

}