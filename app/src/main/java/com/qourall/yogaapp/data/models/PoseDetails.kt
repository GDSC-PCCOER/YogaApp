package com.qourall.yogaapp.data.models

import com.qourall.yogaapp.data.localDB.Category


data class PoseDetails(
    val id : Int,
    val sanskrit_name : String,
    val english_name : String,
    val img_url : String,
    val yoga_categories : List<Category>
)
