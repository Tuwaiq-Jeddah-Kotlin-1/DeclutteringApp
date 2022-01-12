package com.example.declutteringapp.model

import java.io.Serializable
import java.net.PasswordAuthentication

data class UserInfoModel(var name:String,var email:String,var password:String) : Serializable{

        constructor():this("","","")


        fun getUserInfo():String{
            var user = " $email,password:$password"
            return user
        }
    }
