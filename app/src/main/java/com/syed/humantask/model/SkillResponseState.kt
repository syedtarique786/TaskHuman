package com.syed.humantask.model

data class SkillResponseState(
    val response: SkillResponse?,
    val state: ResponseState,
    val error: String?
)