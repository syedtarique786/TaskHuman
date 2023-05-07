package com.syed.humantask.retrofit

import com.syed.humantask.model.*

class Repository(private val api: ApiServices) {


    suspend fun callSkillListApi(): SkillResponseState {
        val response = api.requestAllSkills()
        val skillState: SkillResponseState = if (response.isSuccessful) {
            val state = SkillResponseState(
                response.body() as SkillResponse,
                ResponseState.SUCCESS,
                null
            )
            (state)
        } else {
            val state = SkillResponseState(
                null,
                ResponseState.FAILURE,
                response.errorBody().toString()
            )
            (state)
        }
        return skillState

    }

    suspend fun callAddFavouriteApi(): AddFavState {
        val response = api.requestAddFavourite()
        val episodeState: AddFavState = if (response.isSuccessful) {
            val state = AddFavState(
                response.body() as AddFavouriteResponse,
                ResponseState.SUCCESS,
                null
            )
            state
        } else {
            val state = AddFavState(
                null,
                ResponseState.FAILURE,
                response.errorBody().toString()
            )
            state
        }
        return episodeState
    }

    suspend fun callRemoveFavouriteApi(skillName: RequestBean): RemoveFavState {
        val response = api.requestRemoveFavourite(skillName)
        val episodeState: RemoveFavState = if (response.isSuccessful) {
            val state = RemoveFavState(
                response.body() as RemoveFavouriteResponse,
                ResponseState.SUCCESS,
                null
            )
            state
        } else {
            val state = RemoveFavState(
                null,
                ResponseState.FAILURE,
                response.errorBody().toString()
            )
            state
        }
        return episodeState
    }
}