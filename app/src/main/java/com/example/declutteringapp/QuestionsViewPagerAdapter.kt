package com.example.declutteringapppackage

import com.example.declutteringapp.KeepOrTossModel
import com.example.declutteringapp.R
import com.example.declutteringapp.databinding.QuestionsLayoutBinding


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

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
  /*  override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return listQuestions.add(KeepOrTossModel("have you used it in the last year?", 1))

            }
            1 -> {
                return     listQuestions.add(KeepOrTossModel("have you used it in the last year?", 1))

            }
            2 -> {
                return     listQuestions.add(KeepOrTossModel("have you used it in the last year?", 1))

            }
            else -> {
                return     listQuestions.add(KeepOrTossModel("have you used it in the last year?", 1))

            }
        }
    }*/


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemBinding.ques = listQuestions?.get(position)
    }

}