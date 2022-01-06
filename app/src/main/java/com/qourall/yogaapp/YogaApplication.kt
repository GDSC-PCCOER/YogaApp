package com.qourall.yogaapp

import android.app.Application
import androidx.room.RoomDatabase
import com.qourall.yogaapp.data.localDB.YogaDatabase

class YogaApplication : Application() {
    val database : YogaDatabase by lazy { YogaDatabase.createDB(this) }
}