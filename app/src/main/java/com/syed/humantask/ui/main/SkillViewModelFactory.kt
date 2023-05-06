package com.syed.humantask.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.syed.humantask.retrofit.Repository


@Suppress("UNCHECKED_CAST")
class SkillViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SkillViewModel(repository) as T
    }


}