package com.vishalgupta.hmh.ktordemo.listModule

import kotlinx.serialization.Serializable

@Serializable
data class ModelPost(
    val title:String,
    val body:String,
    val id:Int,
    val userId:Int
)
