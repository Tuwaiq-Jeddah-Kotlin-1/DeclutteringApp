package com.example.declutteringapp

import java.io.Serializable
import java.net.PasswordAuthentication

class UserInfoModel(var userId:String,var userName:String,var userEmail:String,var city:String) : Serializable{

        constructor():this("","","","")


        fun getUserInfo():String{
            var user = "$userName, $userEmail. city: $city"
            return user
        }
    }
