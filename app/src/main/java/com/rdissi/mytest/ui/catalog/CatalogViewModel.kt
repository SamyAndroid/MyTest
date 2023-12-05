package com.rdissi.mytest.ui.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rdissi.mytest.common.Result
import com.rdissi.mytest.domain.model.Media
import com.rdissi.mytest.domain.usecase.GetMixedCatalogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val getMixedCatalogUseCase: GetMixedCatalogUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    sealed class UiState {
        object Loading : UiState()
        data class Success(val medias: List<Media>?) : UiState()
        data class Error(val message: String?) : UiState()
    }

    init {
        getCatalog()
    }

    fun getCatalog() {
        getMixedCatalogUseCase().onEach { result ->
            when (result) {
                is Result.Loading -> {
                    _uiState.value = UiState.Loading
                }
                is Result.Success -> {
                    _uiState.value = UiState.Success(medias = result.data)
                }
                is Result.Error -> {
                    _uiState.value = UiState.Error(result.message)
                }
            }
        }.launchIn(viewModelScope)
    }
}
