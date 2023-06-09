package com.example.composeiqbal.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeiqbal.Repo.TokohPolitikRepository
import com.example.composeiqbal.models.TokohPolitik
import com.example.composeiqbal.ui.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailScreenViewModel(private val repository: TokohPolitikRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<TokohPolitik>> =
        MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<TokohPolitik>>
        get() = _uiState

    fun getTokohPolitikById(id: Int) = viewModelScope.launch {
        _uiState.value = UiState.Loading
        _uiState.value = UiState.Success(repository.getTokohById(id))
    }
}