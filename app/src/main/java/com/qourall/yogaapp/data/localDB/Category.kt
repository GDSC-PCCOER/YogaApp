package com.qourall.yogaapp.data.localDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category(
    @PrimaryKey
    val id : Int,
    val name : String,
    val short_name : String,
    val description : String,
)