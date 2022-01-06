package com.qourall.yogaapp.data.localDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_pose")
data class FavPose(
    @PrimaryKey
    val id : Int,
    val sanskrit_name : String,
    val english_name : String,
    val img_url : String,
)
