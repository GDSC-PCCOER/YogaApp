package com.qourall.yogaapp.data.localDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface YogaDao {

    @Query("SELECT * FROM pose ")
    suspend fun getPoses() : List<Pose>

    @Query("SELECT * FROM pose WHERE id = :poseId")
    suspend fun getAPose(poseId : Int) : Pose

    @Query("SELECT COUNT(*) from category")
    suspend fun isCatTableEmpty() : Int

    @Query("SELECT * FROM category")
    suspend fun getAllCategories() : List<Category>

    @Insert
    suspend fun insertCategory(category: Category)

    @Insert(onConflict = REPLACE)
    suspend fun insertPose(pose: Pose)


    @Query("SELECT COUNT(*) from pose where category = :category")
    suspend fun arePosesAvailable(category: String) : Int

    @Query("SELECT COUNT(*) from pose")
    suspend fun areAllPosesAvailable() : Int

    @Query("SELECT * from pose where category = :category")
    suspend fun getAllCategoryPoses(category: String) : List<Pose>

    @Insert(onConflict = REPLACE)
    suspend fun insertFavPose(pose: FavPose)

    @Query("DELETE FROM fav_pose WHERE id = :poseId")
    suspend fun deleteFavPose(poseId: Int) : Int

    @Query("SELECT EXISTS(SELECT * FROM fav_pose WHERE id = :id)")
    suspend fun isRowIsExist(id : Int) : Boolean

    @Query("SELECT * FROM fav_pose ")
    suspend fun getAllFavPose() : List<FavPose>



}