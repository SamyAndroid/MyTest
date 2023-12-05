package com.rdissi.mytest.ui.story

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rdissi.mytest.common.Result
import com.rdissi.mytest.domain.model.Story
import com.rdissi.mytest.domain.usecase.GetStoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoryViewModel @Inject constructor(
    private val getStoryUseCase: GetStoryUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    sealed class UiState {
        object Loading : UiState()
        data class Success(val story: Story) : UiState()
        data class Error(val message: String?) : UiState()
    }

    sealed class UiEvent {
        data class Toast(val message: String) : UiEvent()
    }

    fun getStoryById(id: Int) {
        viewModelScope.launch {
            when (val result = getStoryUseCase(id)) {
                is Result.Loading -> _uiState.value = UiState.Loading
                is Result.Success -> {
                    _uiState.value = UiState.Success(story = result.data)
                }
                is Result.Error -> {
                    _uiState.value = UiState.Error(result.message)
                    _uiEvent.emit(UiEvent.Toast(result.message))
                }
            }
        }
    }
}
