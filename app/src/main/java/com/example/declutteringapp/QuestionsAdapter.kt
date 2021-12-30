/*
package com.example.declutteringapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.declutteringapp.databinding.QuestionsLayoutBinding

class QuestionsAdapter (val questionsData:List<QuestionsList>): RecyclerView.Adapter<CustomHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
            val bind: QuestionsLayoutBinding =
                DataBindingUtil.inflate(
                  */
/*  LayoutInflater.from(parent.context),
                    R.layout.questions_layout, parent, false)*//*

            LayoutInflater.from(parent.context),R.layout.questions_layout, parent, false)
            return CustomHolder(bind)
        }

        override fun onBindViewHolder(holder: CustomHolder, position: Int) {
            val questions=questionsData[position]
            holder.bind(questions)

        }

        override fun getItemCount(): Int {
            return questionsData.size
        }
    }

    class CustomHolder (val binding :QuestionsLayoutBinding):RecyclerView.ViewHolder(binding.root){
        init {
          //  binding.vm = KeepOrTossViewModel()
        }
        fun bind( questions: QuestionsList) {
         //   binding.ques


        }

    }
*/
