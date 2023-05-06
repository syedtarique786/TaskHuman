package com.syed.humantask.retrofit

import com.syed.humantask.model.AddFavouriteResponse
import com.syed.humantask.model.RemoveFavouriteResponse
import com.syed.humantask.model.SkillResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiServices {

    @GET("v0.8/discover/topicDetails/physical%20fitness")
    suspend fun requestAllSkills(): Response<SkillResponse>

    @POST("v0.8/favorite/add")
    suspend fun requestAddFavourite(): Response<AddFavouriteResponse>

    @GET("v0.8/favorite/remove")
    suspend fun requestRemoveFavourite(): Response<RemoveFavouriteResponse>


    companion object{
        operator fun invoke(): ApiServices {
            return AppRetrofit().apis
        }
    }
}