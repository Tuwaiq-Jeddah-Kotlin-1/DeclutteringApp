package com.example.declutteringapp

import androidx.room.TypeConverter
import com.example.declutteringapp.model.Space
import com.example.declutteringapp.model.ToDeclutter


class Converters {
    @TypeConverter
    fun fromSpace(value: String?): Space? {
        return value?.let { Space(it,it,"0") }

    }

    @TypeConverter
    fun toSpace(item: Space?): String? {
        return item?.toString()
    }
/*
    @TypeConverter
    fun fromToDeclutter(value: Strin g?): ToDeclutter? {
      //  return value?.let { ToDeclutter(it) }
    }
*/

    @TypeConverter
    fun toToDecltter(items: ToDeclutter?): String? {
        return items?.toString()
    }
}