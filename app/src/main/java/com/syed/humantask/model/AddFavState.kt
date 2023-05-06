package com.syed.humantask.model

data class AddFavState(
    val response: AddFavouriteResponse?,
    val state: ResponseState,
    val error: String?
)