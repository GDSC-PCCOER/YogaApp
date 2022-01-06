package com.qourall.yogaapp.network

import com.qourall.yogaapp.data.localDB.FavPose
import com.qourall.yogaapp.data.localDB.Pose
import com.qourall.yogaapp.data.models.CategoryItem
import com.qourall.yogaapp.data.models.PoseDetails
import com.qourall.yogaapp.data.models.PoseItem
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL =
    "https://lightning-yoga-api.herokuapp.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface YogaApiService {

    @GET("/yoga_categories")
    suspend fun getAllCategories() : Response<CategoryItem>

    @GET("/yoga_poses")
    suspend fun getAllPoses() : Response<PoseItem>

    @GET("/yoga_poses/{id}")
    suspend fun getAPose(@Path("id") id : String) : Response<FavPose>

    @GET("/yoga_poses")
    suspend fun getCategoryPoses(@Query("yoga_category_name") name : String) : Response<PoseItem>

    @GET("/yoga_poses/{id}")
    suspend fun getPoseDetails(@Path("id") id: String) : Response<PoseDetails>
}

object YogaApi {
    val retrofitService : YogaApiService by lazy { retrofit.create(YogaApiService::class.java) }
}