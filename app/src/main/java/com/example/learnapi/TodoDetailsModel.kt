package com.example.learnapi
data class TodoDetailsModel(
   val  userId:Int?=null,
    val id:Int?= null,
    val title:String? = null,
    val body:String?=null
)
data class TodoPostModel<T>(
    val  userId:Int,
    val title:String,
    val body:String
)
