package com.syed.humantask.model

import com.google.gson.annotations.SerializedName

data class RemoveFavouriteResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("success") val success: Boolean,
    @SerializedName("errors") val errors: List<Error>,
)