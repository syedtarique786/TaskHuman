package com.syed.humantask.model

import com.google.gson.annotations.SerializedName

data class SkillResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("topicHeader") val topicHeader: TopicHeader,
    @SerializedName("skills") val skillsList: List<Skill>,
    @SerializedName("isNextPage") val isNextPage: Boolean,
    @SerializedName("message") val message: String?
)

data class Skill(
    @SerializedName("tileName") val tileName: String,
    @SerializedName("displayTileName") val displayTileName: String,
    @SerializedName("type") val type: String,
    @SerializedName("dictionaryName") val dictionaryName: String,
    @SerializedName("tileBackground") val tileBackground: String,
    @SerializedName("skillIcon") val skillIcon: String,
    @SerializedName("availability") val availability: Availability,
    @SerializedName("moreProvidersAvailable") val moreProvidersAvailable: Boolean,
    @SerializedName("providerInfo") val providerInfo: List<ProviderInfoX>,
    @SerializedName("isFavorite") val isFavorite: Boolean,
    @SerializedName("columns") val columns: Int,

    @SerializedName("tileColor") val tileColor: String?,
    @SerializedName("blogMetaData") val blogMetaData: BlogMetaData?,
)

data class TopicHeader(
    val tileName: String,
    val type: String,
    val columns: Int,
    val color: String,
    val topicIcon: String
)

data class Availability(
    val color: String,
    val endTime: Long,
    val startTime: Long,
    val status: Int
)

data class BlogMetaData(
    @SerializedName("_id") val _id: String,
    @SerializedName("estReadTime") val estReadTime: Int,
    @SerializedName("link") val link: String,
    @SerializedName("title") val title: String,
    @SerializedName("blogImageUrl") val blogImageUrl: String,
    @SerializedName("providerInfo") val providerInfo: List<ProviderInfo>,
)

data class ProviderInfoX(
    //@SerializedName("endTime") val endTime: Long,
    @SerializedName("profileImage") val profileImage: String,
    @SerializedName("providerId") val providerId: Int,
    //@SerializedName("startTime") val startTime: Long
)

data class ProviderInfo(
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("userId") val userId: Int,
    @SerializedName("imageUrl") val imageUrl: String,

    @SerializedName("groupIcon") val groupIcon: String?,
    @SerializedName("group") val group: String?,
    @SerializedName("profileImage") val profileImage: String,
    @SerializedName("providerId") val providerId: Int,

)
