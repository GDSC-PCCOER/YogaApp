package com.qourall.yogaapp.data.localDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pose")
data class Pose(
    @PrimaryKey(autoGenerate = true)
    val p_key: Int = 0,
    val id : Int,
    val sanskrit_name : String,
    val english_name : String,
    val img_url : String,
    var category: String? = null
)
