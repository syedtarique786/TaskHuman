package com.syed.humantask.model

data class RemoveFavState(
    val response: RemoveFavouriteResponse?,
    val state: ResponseState,
    val error: String?
)