package com.example.declutteringapp

import java.io.Serializable
import java.net.PasswordAuthentication

data class UserInfoModel(var name:String,var email:String,var password:String) : Serializable{

        constructor():this("","","")


        fun getUserInfo():String{
            var user = "$name, $email,password:$password"
            return user
        }
    }
