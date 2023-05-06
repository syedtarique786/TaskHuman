package com.syed.humantask.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syed.humantask.model.*
import com.syed.humantask.retrofit.Repository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SkillViewModel(private val repository: Repository) : ViewModel() {

    private val TAG = SkillViewModel::class.java.name
    private lateinit var job: Job

    private val _skillList = MutableLiveData<List<Skill>>()
    val skillList: LiveData<List<Skill>>
        get() = _skillList

    private val _addToFav = MutableLiveData<AddFavouriteResponse>()
    val addToFav: LiveData<AddFavouriteResponse>
        get() = _addToFav

    private val _removeFromFav = MutableLiveData<RemoveFavouriteResponse>()
    val removeFromFav: LiveData<RemoveFavouriteResponse>
        get() = _removeFromFav


    fun getAllSkill() {
        job = viewModelScope.launch {
            val skillsResponseState : SkillResponseState = repository.callSkillListApi()
            Log.d(TAG, "skillResponse $skillsResponseState")
            val skillResponse = skillsResponseState.response?.skillsList
            Log.d(TAG, "_skillList $skillResponse")
            _skillList.value = skillResponse
        }
    }


    fun addToFavourite() {
        job = viewModelScope.launch {
            val skillResponse = repository.callAddFavouriteApi()
            _addToFav.value = skillResponse.response
        }
    }

    fun removeFromFavourite() {
        job = viewModelScope.launch {
            val skillResponse = repository.callRemoveFavouriteApi()
            _removeFromFav.value = skillResponse.response
        }
    }


    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}