package com.example.composeiqbal.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeiqbal.Repo.TokohPolitikRepository
import com.example.composeiqbal.models.TokohPolitik
import com.example.composeiqbal.ui.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch


class HomeScreenViewModel(
    private val repository: TokohPolitikRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<TokohPolitik>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<TokohPolitik>>>
        get() = _uiState

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) = viewModelScope.launch {
        _query.value = newQuery
        repository.searchPlayer(_query.value)
            .catch {
                _uiState.value = UiState.Error(it.message.toString())
            }
            .collect {
                _uiState.value = UiState.Success(it)
            }
    }



}