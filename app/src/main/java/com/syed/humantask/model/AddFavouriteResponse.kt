package com.syed.humantask.model

import com.google.gson.annotations.SerializedName

data class AddFavouriteResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("favorite") val favorite: Favorite,
    @SerializedName("message") val message: String,
    @SerializedName("showFavoriteToast") val showFavoriteToast: Boolean,
    @SerializedName("success") val success: Boolean,
    @SerializedName("errors") val errors: List<Error>,
)

data class Error(
    @SerializedName("reason") val reason: String
)

data class Favorite(
    @SerializedName("__v") val v: Int,
    @SerializedName("_id") val id: String,
    @SerializedName("categoryName") val categoryName: String,
    @SerializedName("consumerGroup") val consumerGroup: Any,
    @SerializedName("consumerid") val consumerid: Int,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("dictionaryName") val dictionaryName: String,
    @SerializedName("discoverItemId") val discoverItemId: Int?,
    @SerializedName("favoriteBy") val favoriteBy: String,
    @SerializedName("providerGroup") val providerGroup: Any,
    @SerializedName("providerid") val providerid: Int?,
    @SerializedName("updatedAt") val updatedAt: String
)