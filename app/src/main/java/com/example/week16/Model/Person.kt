package com.example.week16.Model

import com.google.gson.annotations.SerializedName

data class Person (
    var id : Int,
    var first_name : String,
    var last_name : String,
    var email : String,
    var createdAt : String,
    var updatedAt : String
)