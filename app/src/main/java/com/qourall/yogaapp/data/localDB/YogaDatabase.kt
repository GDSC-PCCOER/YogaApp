package com.qourall.yogaapp.data.localDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Category::class, Pose::class, FavPose::class], exportSchema = false, version = 7)
abstract class YogaDatabase : RoomDatabase(){
    abstract fun yogaDao() : YogaDao

    companion object{
        @Volatile
        var INSTANCE : YogaDatabase? = null

        fun createDB(context: Context): YogaDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    YogaDatabase::class.java,
                    "yoga_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}