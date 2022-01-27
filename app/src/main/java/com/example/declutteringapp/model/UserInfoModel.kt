package com.example.declutteringapp.model

import java.io.Serializable

data class UserInfoModel(var name:String,var email:String,var password:String) : Serializable{

        constructor():this("","","")

    }
