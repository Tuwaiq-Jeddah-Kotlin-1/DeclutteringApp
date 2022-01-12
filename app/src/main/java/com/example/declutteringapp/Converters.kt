package com.example.declutteringapp

import androidx.room.TypeConverter
import com.example.declutteringapp.model.Space
import com.example.declutteringapp.model.ToDeclutter
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.lang.reflect.Type


class Converters {
    @TypeConverter
    fun fromSpace(value: String?): Space? {
        return value?.let { Space(it,it,it,subData = ArrayList<ToDeclutter>())}

    }

    @TypeConverter
    fun toStepDataList(optionValuesString: String?): ArrayList<ToDeclutter>? {
        if (optionValuesString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<ArrayList<ToDeclutter?>?>() {}.type
        return gson.fromJson<ArrayList<ToDeclutter>>(optionValuesString, type)
    }

    @TypeConverter
    fun fromStepData(optionValues: ArrayList<ToDeclutter?>?): String? {
        if (optionValues == null) {
            return null
        }
        val gson = Gson()
        return gson.toJson(optionValues)
    }

/*    fun fromSubData(list: ArrayList<ToDeclutter>?): String {
        return list?.joinToString(separator = ";") { it.toString() } ?: ""
    }*/


   @TypeConverter
    fun fromString(value: String): ArrayList<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)}

        @TypeConverter
        fun fromListLisr(list: List<String>): String {
            val gson = Gson()
            return gson.toJson(list)
        }
/*

    @TypeConverter
    fun stringToListOfVisits(value: String): ArrayList<ToDeclutter> {
        Array<String>()= value.toCharArray().map { it.toString() }.toTypedArray()
        return  value}
*/

    @TypeConverter
    fun toSpace(space: Space?): String? {
        return space?.toString()
    }


    @TypeConverter
    fun fromToDeclutter(value:  String?): ToDeclutter? {
        return value?.let { ToDeclutter(it,0) }
    }


    @TypeConverter
    fun toToDecltter(items: ToDeclutter?): String? {
        return items?.toString()
    }
}