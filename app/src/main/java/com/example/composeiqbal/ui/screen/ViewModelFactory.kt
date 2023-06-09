package com.example.composeiqbal.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.composeiqbal.Repo.TokohPolitikRepository
import com.example.composeiqbal.ui.screen.detail.DetailScreenViewModel
import com.example.composeiqbal.ui.screen.home.HomeScreenViewModel

class ViewModelFactory (private val repository: TokohPolitikRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        // Home
        if(modelClass.isAssignableFrom(HomeScreenViewModel::class.java)) {
            return HomeScreenViewModel(repository) as T
        }
        // Detail
        if(modelClass.isAssignableFrom(DetailScreenViewModel::class.java)) {
            return DetailScreenViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}