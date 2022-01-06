package com.qourall.yogaapp.repository

import android.util.Log
import com.qourall.yogaapp.YogaApplication
import com.qourall.yogaapp.data.localDB.Category
import com.qourall.yogaapp.data.localDB.FavPose
import com.qourall.yogaapp.data.localDB.Pose
import com.qourall.yogaapp.data.models.CategoryItem
import com.qourall.yogaapp.data.models.PoseDetails
import com.qourall.yogaapp.data.models.PoseItem
import com.qourall.yogaapp.network.YogaApi
import retrofit2.Response

class YogaRepository(application: YogaApplication) {

    private val db = application.database.yogaDao()

    suspend fun getAllCat(): List<Category> {
        return if (db.isCatTableEmpty() == 0) {
            val cat = YogaApi.retrofitService.getAllCategories()
            for (i in cat.body()!!.items) {
                db.insertCategory(i)
            }

            cat.body()!!.items
        } else {
            db.getAllCategories()
        }
    }


    suspend fun getCatAllPoses(name : String): List<Pose> {
        return if (db.arePosesAvailable(name) == 0) {
            val pose = YogaApi.retrofitService.getCategoryPoses(name)
            for (i in pose.body()!!.items) {
                i.category = name
                db.insertPose(i)
            }
            pose.body()!!.items
        } else {
            db.getAllCategoryPoses(name)
        }
    }

    suspend fun getAllPoses(): List<Pose> {
        return if (db.areAllPosesAvailable() != 48) {
            val pose = YogaApi.retrofitService.getAllPoses()
            for (i in pose.body()!!.items) {
                db.insertPose(i)
            }
            pose.body()!!.items
        } else {
            db.getPoses()
        }
    }

    suspend fun getPoseDetails(id :Int) : PoseDetails {
        val pose = YogaApi.retrofitService.getPoseDetails(id.toString())
        return  pose.body()!!
    }

    suspend fun checkFav(id: Int): Boolean {
        return db.isRowIsExist(id)
    }

    suspend fun removeFav(poseId : Int) {
        val delItem = db.deleteFavPose(poseId)
        Log.d("dv", delItem.toString())
    }

    suspend fun addFav(poseId: Int) : Boolean {
        val pose = YogaApi.retrofitService.getAPose(poseId.toString())
        db.insertFavPose(pose.body()!!)
        return true
    }

    suspend fun getAllFavPose(): List<FavPose> {
        return db.getAllFavPose()
    }
}